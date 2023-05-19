package com.company.textutils.aspect;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.company.textutils.exception.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String ACCESS_DENIED = "Access denied!";
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String LIST_JOIN_DELIMITER = ",";
    public static final String FIELD_ERROR_SEPARATOR = ": ";
    public static final String ERROR_MESSAGE_TEMPLATE = "message: %s %n requested uri: %s";

    private static final Logger local_logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String ERRORS_FOR_PATH = "errors {} for path {}";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
	return getExceptionResponseEntity(ex, status, request, Collections.singletonList("Malformed JSON request"));
    }
        
       @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return getExceptionResponseEntity(ex, status, request, errors);
    }

    /**
     * A general handler for all uncaught exceptions
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
	ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
	final HttpStatus status = responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
	final String localizedMessage = exception.getLocalizedMessage();
	final String path = request.getDescription(false);
	String message = (StringUtils.isNotEmpty(localizedMessage) ? localizedMessage : status.getReasonPhrase());
	logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path), exception);
	return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
    }

    /**
     * Build a detailed information about the exception in the response
     */
    private ResponseEntity<Object> getExceptionResponseEntity(final Exception exception, final HttpStatus status,
	    final WebRequest request, final List<String> errors) {
	
	final String errorsMessage = CollectionUtils.isNotEmpty(errors)
		? errors.stream().filter(StringUtils::isNotEmpty).collect(Collectors.joining(LIST_JOIN_DELIMITER))
		: getMessageForStatus(status);
	local_logger.error(ERRORS_FOR_PATH, errorsMessage, request.getDescription(false));

	ApiError response = ApiError.builder().message(getMessageForStatus(status))
		.status(HttpStatus.valueOf(status.value())).timestamp(LocalDateTime.now(ZoneOffset.UTC)).errors(errors).build();

	return new ResponseEntity<Object>(response, status);
    }

    private String getMessageForStatus(HttpStatus status) {
	switch (status) {
	case UNAUTHORIZED:
	    return ACCESS_DENIED;
	case BAD_REQUEST:
	    return INVALID_REQUEST;
	default:
	    return status.getReasonPhrase();
	}
    }
}
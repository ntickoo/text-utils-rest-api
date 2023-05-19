package com.company.textutils.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.textutils.dto.TextInput;
import com.company.textutils.dto.ValidationResultInfo;
import com.company.textutils.service.TextUtilsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(path = { "/api/v1/text" }, produces = APPLICATION_JSON_VALUE)
@Tag(name = "TextUtils", description = "API Endpoints for utility functions for string/text.")
public class TextUtilsController {

    private final TextUtilsService textUtilsService;
    
    private final ModelMapper modelMapper;

    @Autowired
    public TextUtilsController(TextUtilsService textUtilsService, ModelMapper modelMapper) {
	this.textUtilsService = textUtilsService;
	this.modelMapper = modelMapper;
    }

    @Operation(summary = "Checks if the input string contains all english alphabets ignoring the case.", tags = {
	    "TextUtils" }, responses = {
		    @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationResultInfo.class))),
		    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
		    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content) })
    @PostMapping(path = "/value:has-all-english-chars")
    public ResponseEntity<ValidationResultInfo> hasAllEnglishCharacters(@Valid @RequestBody TextInput inputText) {
	log.info("hasAllEnglishCharacters, param {}", inputText.getValue());

	final boolean result = textUtilsService.containsAllEnglishCharactersIgnoreCase(inputText.getValue());
	return ResponseEntity.ok(modelMapper.map(result, ValidationResultInfo.class));
    }
}

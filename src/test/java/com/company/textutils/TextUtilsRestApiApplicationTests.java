package com.company.textutils;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.company.textutils.dto.TextInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class TextUtilsRestApiApplicationTests {

    private final String API_PATH = "/api/v1/text/value:has-all-english-chars";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void submitAllEnglishChars_shouldReturnTrue() throws JsonProcessingException, Exception {
	TextInput dto = new TextInput("abcdefghijklmnopqrstuvwXyz444");

	this.mockMvc
		.perform(post(API_PATH).contentType("application/json").content(objectMapper.writeValueAsString(dto)))
		.andExpect(status().isOk()).andExpect(jsonPath("$.result").exists())
		.andExpect(jsonPath("$.result").isBoolean())
		.andExpect(jsonPath("$.result", is(true)));
    }
    
    @Test
    void nullInput_shouldReturnBadRequest() throws JsonProcessingException, Exception {
	TextInput dto = new TextInput(null);

	this.mockMvc
		.perform(post(API_PATH).contentType("application/json").content(objectMapper.writeValueAsString(dto)))
		.andExpect(status().is4xxClientError());
    }
    
    @Test
    void invalidInput_shouldReturnBadRequest() throws JsonProcessingException, Exception {
	Map<String, String> testInput = new HashMap<>();
	testInput.put("Hello", "world");
	this.mockMvc
		.perform(post(API_PATH).contentType("application/json").content(objectMapper.writeValueAsString(testInput)))
		.andExpect(status().is4xxClientError());
    }
    
}

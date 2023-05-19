package com.company.textutils.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class TextUtilsServiceTest {
    
    @Test
    void containsAllEnglishCharactersIgnoreCase_nullOrEmptyInput_shouldReturnFalse() {
	// Arrange
	TextUtilsService textUtilsService = new TextUtilsService();
	
	// Act
	final boolean resultEmptyInput = textUtilsService.containsAllEnglishCharactersIgnoreCase("");
	
	// Assert
	assertThat(resultEmptyInput).isFalse();
	
	// Act
	final boolean resultNullInput = textUtilsService.containsAllEnglishCharactersIgnoreCase(null);
	
	// Assert
	assertThat(resultNullInput).isFalse();
    }

    @Test
    void containsAllEnglishCharactersIgnoreCase_inputHasLowerUpperCaseLettersMixed_shouldReturnTrue() {
	// Arrange
	TextUtilsService textUtilsService = new TextUtilsService();
	final String input = "2323~@!AaBb2323CcDdEfGhI232JklMnopQrS23tuVWXyzksld";
	
	// Act
	final boolean result = textUtilsService.containsAllEnglishCharactersIgnoreCase(input);
	
	// Assert
	assertThat(result).isTrue();
    }
    
    @Test
    void containsAllEnglishCharactersIgnoreCase_inputDoesNotHaveAllAlphabets_shouldReturnFalse() {
	// Arrange
	TextUtilsService textUtilsService = new TextUtilsService();
	final String input = "2323~@!AaBb2323CcDdEfGhI232";
	
	// Act
	final boolean result = textUtilsService.containsAllEnglishCharactersIgnoreCase(input);
	
	// Assert
	assertThat(result).isFalse();
    }
    
    
    @Test
    void containsAllEnglishCharactersIgnoreCase_inputHasOnlyAllUpperCaseLetters_shouldReturnTrue() {
	// Arrange
	TextUtilsService textUtilsService = new TextUtilsService();
	final String input = "232A3~@!ABCDEFGHIJKLMNOP2323QRS5656TU7676(*&(*&(&*VWXYZ";
	
	// Act
	final boolean result = textUtilsService.containsAllEnglishCharactersIgnoreCase(input);
	
	// Assert
	assertThat(result).isTrue();
    }
    
    @Test
    void containsAllEnglishCharactersIgnoreCase_inputHasNoLetters_shouldReturnFalse() {
	// Arrange
	TextUtilsService textUtilsService = new TextUtilsService();
	final String input = "2304982034982304983@309820398)@(#8)@(*)(*)(#*$)(*%$#)($*#)$*#)$*)*65+698*-/-*-";
	
	// Act
	final boolean result = textUtilsService.containsAllEnglishCharactersIgnoreCase(input);
	
	// Assert
	assertThat(result).isFalse();
    }
    
    
}

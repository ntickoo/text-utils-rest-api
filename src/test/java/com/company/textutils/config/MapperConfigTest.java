package com.company.textutils.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.company.textutils.dto.ValidationResultInfo;

class MapperConfigTest {

    @Test
    public void mapperTest_booleanToValidationResult_shouldMapInput() {
	// Arrange
	ModelMapper mapper = new MapperConfig().modelMapper();
	
	// Act
	final ValidationResultInfo resultFalse = mapper.map(Boolean.FALSE, ValidationResultInfo.class);
	
	// Assert
	assertThat(resultFalse).isNotNull();
	assertThat(resultFalse.getResult()).isEqualTo(Boolean.FALSE);
	
	// Act
	final ValidationResultInfo resultTrue = mapper.map(Boolean.TRUE, ValidationResultInfo.class);
	
	// Assert
	assertThat(resultTrue).isNotNull();
	assertThat(resultTrue.getResult()).isEqualTo(Boolean.TRUE);
    }

}

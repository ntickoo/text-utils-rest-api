package com.company.textutils.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.textutils.dto.ValidationResultInfo;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
	final ModelMapper mapper = new ModelMapper();
	mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

	final TypeMap<Boolean, ValidationResultInfo> booleanToValidationResultTypeMap = mapper.createTypeMap(Boolean.class, ValidationResultInfo.class);

	booleanToValidationResultTypeMap.addMapping(Boolean::booleanValue, ValidationResultInfo::setResult);

	return mapper;
    }
}
package com.company.textutils.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextInput {
    @NotNull(message = "Invalid value passed. Value is NULL")
    private String value;
}


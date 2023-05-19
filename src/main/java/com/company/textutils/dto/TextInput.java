package com.company.textutils.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TextInput {
    @NotNull(message = "Invalid value passed. Value is NULL")
    private String value;
}


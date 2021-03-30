package com.indomdi.com.core.dto;

import lombok.Data;

@Data
public class RegisterResponseDto {
    private Boolean success;
    private String message;
    private String secureCode;
}

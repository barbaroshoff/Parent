package com.indomdi.com.core.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String secureCode;
    private String newPassword;
}

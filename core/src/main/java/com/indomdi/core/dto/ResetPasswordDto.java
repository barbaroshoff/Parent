package com.indomdi.core.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String secureCode;
    private String newPassword;
}

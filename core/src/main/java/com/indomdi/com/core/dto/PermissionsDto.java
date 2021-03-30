package com.indomdi.com.core.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class PermissionsDto {
    @Column(name = "enabled", nullable = false, length = 25)
    Boolean enabled;
}

package com.indomdi.com.core.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class ListRoleDto {

    @Column(name = "role", nullable = true, length = 25)
    private List<RoleListDto> role;
}

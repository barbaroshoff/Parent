package com.indomdi.com.core.dto;

import com.indomdi.com.core.config.GuiRoles;
import lombok.Data;

import javax.persistence.Column;

@Data
public class RoleListDto {

    @Column(name="role", nullable = true,length = 25)
    private GuiRoles guiRoles;
    @Column(name="created", nullable = true,length = 25)
    private String created;
}

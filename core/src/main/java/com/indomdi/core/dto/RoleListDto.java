package com.indomdi.core.dto;

import com.indomdi.core.config.GuiRoles;
import lombok.Data;

import javax.persistence.Column;

@Data
public class RoleListDto {

    @Column(name="role", nullable = true,length = 25)
    private GuiRoles guiRoles;
    @Column(name="created", nullable = true,length = 25)
    private String created;
}

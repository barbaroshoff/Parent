package com.indomdi.core.dto;


import lombok.Data;

import javax.persistence.Column;

@Data
public class UsersListDto {

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    @Column(name = "organization", nullable = true, length = 80)
    private String organization;
    @Column(name = "enabled", nullable = false, length = 25)
    private Boolean enabled;

}

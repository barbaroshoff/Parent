package com.indomdi.core.dto;

import com.indomdi.core.config.GuiRoles;
import lombok.Data;
import javax.persistence.Column;

@Data
public class UsersViewDto {

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    @Column(name="role", nullable = true,length = 25)
    private GuiRoles guiRoles;
    @Column(name = "firstName", nullable = true, length = 25)
    private String firstName;
    @Column(name = "lastName", nullable = true, length = 40)
    private String lastName;
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    @Column(name = "country", nullable = true, length = 30)
    private String country;
    @Column(name = "city", nullable = true, length = 40)
    private String city;
    @Column(name = "securityAnswer", nullable = true, length = 80)
    private String securityAnswer;
    @Column(name = "organization", nullable = true, length = 80)
    private String organization;
    @Column(name = "securityQuestion", nullable = true, length = 80)
    private String securityQuestion;
}

package com.indomdi.core.dto;


import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UsersListDto {

    @NotNull(message = "'Username' cannot be null or empty")
    @Size(min = 3, max = 50, message = "'Username' length must be in [3..50] characters range")
    @Pattern(regexp = "[\\w-\\_\\.\\-]+", message = "Invalid username pattern")
    private String username;
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    @Column(name = "organization", nullable = true, length = 80)
    private String organization;
    @Column(name = "enabled", nullable = false, length = 25)
    private Boolean enabled;

}

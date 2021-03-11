package com.indomdi.core.dto;


import com.indomdi.core.persistent.Authorities;
import lombok.Data;

import java.util.List;

@Data
public class UserDetailsDto {
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String username;
    private String created;
    private List<Authorities> roles;
}

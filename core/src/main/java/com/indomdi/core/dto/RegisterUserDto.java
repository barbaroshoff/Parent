package com.indomdi.core.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterUserDto {

    @NotNull
    @Size(min = 3, max = 25, message = "'First Name' length must be in [2..25] characters range")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 40, message = "'Last Name' length must be in [2..40] characters range")
    private String lastName;

    @NotNull(message = "'Email' cannot be null or empty")
    @Size(min = 5, max = 50, message = "'First Name' length must be in [5..50] characters range")
    // @Pattern(regexp = "(.*)@([\\w-\\.]{2,}).\\w+", message = "Invalid email pattern")
    @Email(message = "Invalid email pattern")
    private String email;

    @NotNull
    private String country;

    @NotNull
    @Size(min = 0, max = 80, message = "'City' length must be in [0..80] characters range")
    private String city;

    @NotNull
    @Size(min = 0, max = 80, message = "'Organization' length must be in [0..80] characters range")
    private String organization;

    @NotNull
    @Size(min = 0, max = 80, message = "'Security Answer' length must be in [0..80] characters range")
    private String securityAnswer;

    @NotNull
    @Size(min = 0, max = 280, message = "'Security Answer' length must be in [0..280] characters range")
    private String securityQuestion;

    @NotNull(message = "'Username' cannot be null or empty")
    @Size(min = 3, max = 50, message = "'Username' length must be in [3..50] characters range")
    @Pattern(regexp = "[\\w-\\_\\.\\-]+", message = "Invalid username pattern")
    private String username;

    @NotNull(message = "'Password' cannot be null or empty")
    @Size(min = 3, max = 255, message = "'Password' length must be in [3..255] characters range")
    private String password;
}

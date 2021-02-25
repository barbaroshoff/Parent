package com.indomdi.core.converter;


import com.indomdi.core.config.GuiRoles;
import com.indomdi.core.dto.RegisterUserDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.indomdi.core.persistent.Authorities;
import com.indomdi.core.persistent.RegisterUser;
import com.indomdi.core.persistent.Users;

@Component
public class RegisterUserConverter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterUser toSignupUser(RegisterUserDto dto) {
        final RegisterUser user = new RegisterUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setCountry(dto.getCountry());
        user.setUsername(dto.getUsername());
        user.setCity(dto.getCity());
        user.setSecurityQuestion(dto.getSecurityQuestion());
        user.setOrganization(dto.getOrganization());
        user.setSecurityAnswer(dto.getSecurityAnswer());
        user.setRole(GuiRoles.USER);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        final String secureCode = RandomStringUtils.randomAlphanumeric(64);
        user.setSecureCode(secureCode);

        return user;
    }

    public Users toUser(RegisterUser s) {
        final Users u = new Users();
        u.setFirstName(s.getFirstName());
        u.setLastName(s.getLastName());
        u.setEmail(s.getEmail());
        u.setGuiRoles(s.getRole());
        u.setCountry(s.getCountry());
        u.setCity(s.getCity());
        u.setSecurityQuestion(s.getSecurityQuestion());
        u.setOrganization(s.getOrganization());
        u.setSecurityAnswer(s.getSecurityAnswer());
        u.setUsername(s.getUsername());
        u.setPassword(s.getPassword());
        u.setEnabled(true);
        return u;
    }

    public Authorities autorityForUser(Users user) {
        final Authorities authority = new Authorities();
        authority.setUser(user);
        authority.setAuthority(GuiRoles.USER.authority().getAuthority());
        return authority;
    }
}

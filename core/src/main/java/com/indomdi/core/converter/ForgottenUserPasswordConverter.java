package com.indomdi.core.converter;


import com.indomdi.core.dao.UsersDao;
import com.indomdi.core.dto.ForgottenPasswordRequestDto;
import com.indomdi.core.persistent.ForgottenUserPassword;
import com.indomdi.core.persistent.Users;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ForgottenUserPasswordConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersDao usersDao;

    public ForgottenUserPassword toForgottenpasswordUser(ForgottenPasswordRequestDto dto) throws Exception {
        final ForgottenUserPassword user = new ForgottenUserPassword();
        user.setEmail(dto.getEmail());


        Users u = usersDao.findByEmail(dto.getEmail());
        System.out.println(u.getFirstName()+"blsw");
        if (u == null) {
            throw new Exception("User not found for " + dto.getEmail());
        }


        user.setUsername(u.getUsername());


        final String secureCode = RandomStringUtils.randomAlphanumeric(64);
        user.setSecureCode(secureCode);
        user.setPassword(u.getPassword());

        return user;
    }
}

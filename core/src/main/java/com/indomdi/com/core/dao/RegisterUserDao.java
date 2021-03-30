package com.indomdi.com.core.dao;

import com.indomdi.com.core.persistent.RegisterUser;

import java.util.Optional;

//@Repository
public interface RegisterUserDao extends AbstractDao<RegisterUser> {

    Optional<RegisterUser> findByUsername(String username);

    Optional<RegisterUser> findBySecureCode(String secureCode);
}

package com.indomdi.com.core.dao;

import com.indomdi.com.core.persistent.Users;

import java.util.List;
import java.util.Optional;


//@Repository
public interface UsersDao   extends AbstractDao<Users> {

    Optional<Users> findByUsername(String username);

    void deleteByUsername(String username);

    Users findByEmail(String email);

    List<Users> findAllByEmail(String email);

    List<Users> findAll();
}

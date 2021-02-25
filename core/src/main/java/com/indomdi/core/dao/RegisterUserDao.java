package com.indomdi.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.indomdi.core.persistent.Authorities;
import com.indomdi.core.persistent.RegisterUser;

import java.util.Optional;

@Repository
public interface RegisterUserDao extends JpaRepository<RegisterUser,String> {
    Optional<RegisterUser> findByUsername(String username);

    Optional<RegisterUser> findBySecureCode(String secureCode);
}

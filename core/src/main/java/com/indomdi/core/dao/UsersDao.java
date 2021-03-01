package com.indomdi.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.indomdi.core.persistent.Users;

import java.util.Optional;


@Repository
public interface UsersDao   extends JpaRepository<Users,String> {

    Optional<Users> findByUsername(String username);

    void deleteByUsername(String username);

    Optional<Users> findByEmail(String email);
}

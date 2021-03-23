package com.indomdi.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.indomdi.core.persistent.Users;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface UsersDao   extends JpaRepository<Users,String> {
    Optional<Users> findByUsername(String username);

    
    void deleteByUsername(String username);

    Users findByEmail(String email);

    List<Users> findAllByEmail(String email);

    List<Users> findAll();
}

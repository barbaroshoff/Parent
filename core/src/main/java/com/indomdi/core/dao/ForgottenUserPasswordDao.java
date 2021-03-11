package com.indomdi.core.dao;

import com.indomdi.core.persistent.ForgottenUserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ForgottenUserPasswordDao  extends JpaRepository<ForgottenUserPassword,String> {

    Optional<ForgottenUserPassword> findByUsername(String username);

    Optional<ForgottenUserPassword> findBySecureCode(String secureCode);

    List<ForgottenUserPassword> findByCreatedLessThan(LocalDateTime date);
}

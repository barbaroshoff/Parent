package com.indomdi.com.core.dao;

import com.indomdi.com.core.persistent.ForgottenUserPassword;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ForgottenUserPasswordDao  extends AbstractDao<ForgottenUserPassword> {

    Optional<ForgottenUserPassword> findByUsername(String username);

    Optional<ForgottenUserPassword> findBySecureCode(String secureCode);

    List<ForgottenUserPassword> findByCreatedLessThan(LocalDateTime date);
}

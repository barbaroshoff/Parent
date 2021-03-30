package com.indomdi.com.core.dao;

import com.indomdi.com.core.persistent.Authorities;
import lombok.NonNull;

import java.util.List;

//@Repository
public interface AuthoritiesDao extends AbstractDao<Authorities> {

    List<Authorities> findByUsername(@NonNull String userName);
}

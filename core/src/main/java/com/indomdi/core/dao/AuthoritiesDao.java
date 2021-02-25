package com.indomdi.core.dao;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.indomdi.core.persistent.Authorities;

import java.util.List;

@Repository
public interface AuthoritiesDao extends JpaRepository<Authorities,String> {

    List<Authorities> findByUsername(@NonNull String userName);

}

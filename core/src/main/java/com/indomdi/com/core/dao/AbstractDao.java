package com.indomdi.com.core.dao;


import com.indomdi.com.core.persistent.common.Identifiable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractDao<T extends Identifiable> extends JpaRepository<T, String> {

    default void delete(String id) {
        if (findById(id).isPresent()) deleteById(id);
    }
}

package com.indomdi.core.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.indomdi.core.persistent.common.Identifiable;

@NoRepositoryBean
public interface AbstractDao<T extends Identifiable> extends JpaRepository<T, String> {

    /**
     * Silent entity deletion
     * <p/>
     * Does not throw {@link javax.persistence.EntityNotFoundException} in case of non-existent id
     *
     * @param id
     */

    default void delete(String id) {
        if (findById(id).isPresent()) deleteById(id);
    }

}

package com.indomdi.com.core.persistent.common;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class Identifiable implements Serializable {
    private static final long serialVersionUID = 5558666022039327323L;

    private static final String UUID_GENERATOR = "system-uuid";

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(generator = UUID_GENERATOR)
    @GenericGenerator(name = UUID_GENERATOR, strategy = "uuid2")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Identifiable)) {
            return false;
        }
        final Identifiable other = (Identifiable) obj;
        return Objects.equals(id, other.id);
    }
}

package com.indomdi.core.persistent;


import com.indomdi.core.persistent.common.AuditedEntity;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITIES", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "authority"})})
public class Authorities extends AuditedEntity {

    private static final long serialVersionUID = 5561869624538581964L;

    @Column(name = "username", nullable = false, length = 50, unique = true, insertable = false, updatable = false)
    private String username;
    @Column(name = "authority", nullable = false, length = 50)
    private String authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private Users user;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * @param authority the authority to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Users user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Authorities{" +
                "username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}

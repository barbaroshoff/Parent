package com.indomdi.com.core.persistent;



import com.indomdi.com.core.persistent.common.AuditedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;


@Entity
@Table(name = "FORGOTTEN_USER_PASSWORDS", indexes = { @Index(columnList = "username", unique = true) })
public class ForgottenUserPassword extends AuditedEntity {

    private static final long serialVersionUID = -7923360064479999122L;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "secureCode", nullable = false, length = 65)
    private String secureCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ForgottenUserPassword other = (ForgottenUserPassword) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (secureCode == null) {
            if (other.secureCode != null)
                return false;
        } else if (!secureCode.equals(other.secureCode))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
}

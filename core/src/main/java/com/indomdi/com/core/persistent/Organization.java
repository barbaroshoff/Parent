package com.indomdi.com.core.persistent;

import com.indomdi.com.core.persistent.common.AuditedEntity;

import javax.persistence.*;

@Entity
@Table(name = "ORGANIZATION")
public class Organization extends AuditedEntity {

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Column(name = "organization", nullable = true, length = 80)
    private String organization;
    @Column(name = "country", nullable = true, length = 30)
    private String country;
    @Column(name = "city", nullable = true, length = 40)
    private String city;

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setCity(String city) {
        this.city = city;
    }


}

package com.indomdi.com.core.dto.Organization;

import lombok.Data;

import javax.persistence.Column;

@Data
public class OrganizationAddDto {

    @Column(name = "organization", nullable = true, length = 80)
    private String organization;
    @Column(name = "country", nullable = true, length = 30)
    private String country;
    @Column(name = "city", nullable = true, length = 40)
    private String city;
}

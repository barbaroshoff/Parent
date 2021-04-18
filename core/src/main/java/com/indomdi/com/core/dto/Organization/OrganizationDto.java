package com.indomdi.com.core.dto.Organization;

import lombok.Data;

import javax.persistence.Column;

@Data
public class OrganizationDto {

    private String organization;
    private String country;
    private String city;
}

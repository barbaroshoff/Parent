package com.indomdi.com.core.converter;

import com.indomdi.com.core.dto.Organization.OrganizationDto;
import com.indomdi.com.core.persistent.Organization;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganizationConverter {

    public Organization toEntity (OrganizationDto organizationDto){
        Organization organization = new Organization();
        organization.setOrganization(organizationDto.getOrganization());
        organization.setCity(organizationDto.getCity());
        organization.setCountry(organizationDto.getCountry());
        return organization;
    }

    public OrganizationDto toDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto();

        organizationDto.setOrganization(organization.getOrganization());
        organizationDto.setCity(organization.getCity());
        organizationDto.setCountry(organization.getCountry());
        return organizationDto;
    }

    public List<OrganizationDto> toDtoList(List<Organization> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Organization toOrganizationEdit(@NotNull OrganizationDto organizationDto, Organization organization){
        organization.setOrganization(organizationDto.getOrganization());
        organization.setCity(organizationDto.getCity());
        organization.setCountry(organizationDto.getCountry());
        return organization;
    }
}

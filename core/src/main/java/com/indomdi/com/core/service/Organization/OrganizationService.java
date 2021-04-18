package com.indomdi.com.core.service.Organization;

import com.indomdi.com.core.dto.Organization.OrganizationDto;
import com.indomdi.com.core.dto.PermissionsDto;

public interface OrganizationService {

    void edit(String organizationName, OrganizationDto organizationDto);

//    void deleteOrganization(OrganizationDto organizationDto);

    OrganizationDto view(String organization);

    OrganizationDto organizationAdd(OrganizationDto organizationDto);

    void editPermissions(String organisationName, PermissionsDto permissionsDto);
}

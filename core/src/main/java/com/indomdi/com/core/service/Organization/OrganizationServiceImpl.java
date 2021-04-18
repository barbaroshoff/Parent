package com.indomdi.com.core.service.Organization;

import com.indomdi.com.core.converter.OrganizationConverter;
import com.indomdi.com.core.dao.OrganizationDao;
import com.indomdi.com.core.dto.Organization.OrganizationDto;
import com.indomdi.com.core.dto.PermissionsDto;
import com.indomdi.com.core.persistent.Organization;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private OrganizationConverter organizationConverter;


    @Override
    public void edit(String organizationName, OrganizationDto organizationDto) {

        Organization organization = organizationDao.findByOrganization(organizationName).get();
        Organization organizationEdit = organizationConverter.toOrganizationEdit(organizationDto, organization);
        organizationDao.save(organizationEdit);
    }

    @Override
    public OrganizationDto organizationAdd(OrganizationDto organizationDto) {

        Optional<Organization> byOrganization = organizationDao.findByOrganization(organizationDto.getOrganization());
        if(!byOrganization.isPresent()){
            Organization organization = organizationConverter.toEntity(organizationDto);
            organizationDao.save(organization);
        } else {
            System.out.println("Error");
        }
        return organizationDto;
    }

    @Override
    public OrganizationDto view(String organization) {

        try {
            Organization organizationName = organizationDao.findByOrganization(organization).get();
            OrganizationDto organizationDto = organizationConverter.toDto(organizationName);
            return organizationDto;
        } catch (Exception e){
            System.out.println("Error");
        }
        return null;
    }

    @Override
    public void editPermissions(String organisationName, PermissionsDto permissionsDto) {

        Organization organization = organizationDao.findByOrganization(organisationName).get();
        organization.setEnabled(permissionsDto.getEnabled());
        organizationDao.save(organization);
    }

}

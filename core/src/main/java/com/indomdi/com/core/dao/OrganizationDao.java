package com.indomdi.com.core.dao;

import com.indomdi.com.core.persistent.Organization;

import java.util.Optional;

public interface OrganizationDao extends AbstractDao<Organization> {

    Optional<Organization> findByOrganization(String organizationName);

//    void delete(String organizationName);
}

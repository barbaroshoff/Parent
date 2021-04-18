package com.indomdi.com.web.controller;

import com.indomdi.com.core.dto.Organization.OrganizationDto;
import com.indomdi.com.core.dto.PermissionsDto;
import com.indomdi.com.core.service.Organization.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Organization API")
@RequestMapping(value = "/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Edit user details by username")
    public ResponseEntity<String> edit(@PathVariable("organizationName") String organizationName,
                                       @RequestBody OrganizationDto organizationDto) {

        organizationService.edit(organizationName, organizationDto);
        return ResponseEntity.ok().body("ok");
    }

    @PutMapping(path = "/permissions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Edit user details by username")
    public ResponseEntity<String> editPermissions(@PathVariable("organisationName") String organizationName,
                                                  @RequestBody PermissionsDto permissionsDto){

        organizationService.editPermissions(organizationName, permissionsDto);

        return ResponseEntity.ok().body("ok");
    }

//    @DeleteMapping(value = "/delete")
//    public ResponseEntity<String> deleteOrganization(@RequestBody OrganizationDto organizationDto) {
//
//        organizationService.deleteOrganization(organizationDto);
//
//        return ResponseEntity
//                .ok()
//                .body("ok");
//    }

    @GetMapping(path = "/view", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationDto> view(@PathVariable("organization") String organization) {

        OrganizationDto view = organizationService.view(organization);

        return ResponseEntity
                .ok()
                .body(view);
    }

    @GetMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get (paginated) organisation add")
    public ResponseEntity<OrganizationDto> organizationAdd(@RequestBody OrganizationDto organizationDto) {

        OrganizationDto add = organizationService.organizationAdd(organizationDto);

        return ResponseEntity
                .ok(add);
    }
}

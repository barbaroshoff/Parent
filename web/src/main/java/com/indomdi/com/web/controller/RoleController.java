package com.indomdi.com.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.indomdi.com.core.dto.ListRoleDto;
import com.indomdi.com.core.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Role API")
@RequestMapping(value = "/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get (paginated) role list")
    public ResponseEntity<ListRoleDto> getRoleList() throws JsonProcessingException {
        ListRoleDto roleList = roleService.getRoleList();
        return ResponseEntity
                .ok(roleList);
    }
}

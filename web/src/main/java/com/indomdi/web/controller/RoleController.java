package com.indomdi.web.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indomdi.core.dao.UsersDao;
import com.indomdi.core.dto.ListRoleDto;
import com.indomdi.core.dto.RoleListDto;
import com.indomdi.core.persistent.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Role API")
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private UsersDao usersDao;

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get (paginated) role list")
    public ResponseEntity<ListRoleDto> getRoleList() throws JsonProcessingException {

        final List<Users> result = usersDao.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        ModelMapper modelMapper = new ModelMapper();
        ListRoleDto listRoleDto = new ListRoleDto();

        List<RoleListDto> role = new ArrayList<>();
        result.stream().forEach(user -> {
            RoleListDto roleListDto =  modelMapper.map(user, RoleListDto.class);
            role.add(roleListDto);
        });
        listRoleDto.setRole(role);
        return ResponseEntity
                .ok(listRoleDto);
    }
}

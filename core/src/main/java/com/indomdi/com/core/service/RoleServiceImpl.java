package com.indomdi.com.core.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indomdi.com.core.dao.UsersDao;
import com.indomdi.com.core.dto.ListRoleDto;
import com.indomdi.com.core.dto.RoleListDto;
import com.indomdi.com.core.persistent.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UsersDao usersDao;
    @Override
    public ListRoleDto getRoleList() {

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
        return listRoleDto;
    }
}

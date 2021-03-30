package com.indomdi.com.core.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indomdi.com.core.dao.UsersDao;
import com.indomdi.com.core.dto.*;
import com.indomdi.com.core.persistent.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public void editAdmin(String userName, UserEditDto userDto) {

        Users users = usersDao.findByUsername(userName).get();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setCountry(userDto.getCountry());
        users.setCity(userDto.getCity());
        users.setSecurityQuestion(userDto.getSecurityQuestion());
        users.setOrganization(userDto.getOrganization());
        users.setSecurityAnswer(userDto.getSecurityAnswer());
        usersDao.save(users);
    }

    @Override
    public void editPermissions(String userName, PermissionsDto permissionsDto) {
        Users users = usersDao.findByUsername(userName).get();
        users.setEnabled(permissionsDto.getEnabled());
        usersDao.save(users);
    }

    @Override
    public UsersViewDto monitor(String userName) {

        Users users = usersDao.findByUsername(userName).get();
        ModelMapper modelMapper = new ModelMapper();
        UsersViewDto usersViewDto = modelMapper.map(users, UsersViewDto.class);
        return usersViewDto;
    }

    @Override
    public UserListDto getUsersList() {

        final List<Users> result = usersDao.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        ModelMapper modelMapper = new ModelMapper();
        UserListDto userListDto = new UserListDto();

        List<UsersListDto> users = new ArrayList<>();
        result.stream().forEach(user -> {
            UsersListDto usersListDto =  modelMapper.map(user, UsersListDto.class);
            users.add(usersListDto);
        });
        userListDto.setUsers(users);
        return userListDto;
    }
}

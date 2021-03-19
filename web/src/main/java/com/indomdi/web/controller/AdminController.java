package com.indomdi.web.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indomdi.core.dao.UsersDao;
import com.indomdi.core.dto.*;
import com.indomdi.core.persistent.Users;
import com.indomdi.core.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Admin API")
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private RegisterService signupService;

    @Autowired
    private UsersDao usersDao;

    // equals administration as in technical task
    @PutMapping(path = "/edit/{userName:.*}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Edit user details by username")
    public ResponseEntity<String> edit(@PathVariable("userName") String userName,
                                       @RequestBody UserEditDto userDto){
        Users users = usersDao.findByUsername(userName).get();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setCountry(userDto.getCountry());
        users.setCity(userDto.getCity());
        users.setSecurityQuestion(userDto.getSecurityQuestion());
        users.setOrganization(userDto.getOrganization());
        users.setSecurityAnswer(userDto.getSecurityAnswer());
        usersDao.save(users);
        return ResponseEntity.ok().body("ok");
    }

    @PutMapping(path = "/permissions/{userName:.*}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Edit user details by username")
    public ResponseEntity<String> editPermissions(@PathVariable("userName") String userName,
                                                  @RequestBody PermissionsDto permissionsDto){
        Users users = usersDao.findByUsername(userName).get();
        users.setEnabled(permissionsDto.getEnabled());
        usersDao.save(users);
        return ResponseEntity.ok().body("ok");
    }

    @DeleteMapping(value = "/delete/{userName:.*}")
    public ResponseEntity<String> deleteUser(@PathVariable("userName") String userName) {
        usersDao.deleteByUsername(userName);
        return ResponseEntity
                .ok()
                .body("ok");
    }

    @GetMapping(path = "/monitoring/{userName:.*}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersViewDto> monitor(@PathVariable("userName") String userName) {
        Users users = usersDao.findByUsername(userName).get();
        ModelMapper modelMapper = new ModelMapper();
        UsersViewDto usersViewDto = modelMapper.map(users, UsersViewDto.class);

        return ResponseEntity
                .ok()
                .body(usersViewDto);
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get (paginated) users list")
    public ResponseEntity<UserListDto> getUsersList() throws JsonProcessingException {

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
        return ResponseEntity
                .ok(userListDto);
    }

}


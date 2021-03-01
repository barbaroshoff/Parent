package com.indomdi.web.controller;

import com.indomdi.core.dao.UsersDao;
import com.indomdi.core.dto.RegisterResponseDto;
import com.indomdi.core.dto.RegisterUserDto;
import com.indomdi.core.dto.UserEditDto;
import com.indomdi.core.exception.RegisterUserException;
import com.indomdi.core.persistent.Users;
import com.indomdi.core.persistent.common.ResponseDTO;
import com.indomdi.core.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Signup API")
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private RegisterService signupService;

    @Autowired
    private UsersDao usersDao;


    // equals administration as in technical task
    @PutMapping(path = "/edit/{userName:.*}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Edit user details by username")
    public ResponseEntity<String> edit(@PathVariable("userName") String userName,@RequestBody UserEditDto userDto){
        Users users = usersDao.findByUsername(userName).get();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setEmail(userDto.getEmail());
        users.setCountry(userDto.getCountry());
        users.setCity(userDto.getCity());
        users.setSecurityQuestion(userDto.getSecurityQuestion());
        users.setOrganization(userDto.getOrganization());
        users.setSecurityAnswer(userDto.getSecurityAnswer());
        usersDao.save(users);
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping(path = "/monitoring/{userName:.*}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> monitor(@PathVariable("userName") String userName) throws RegisterUserException {
        Users users = usersDao.findByUsername(userName).get();
        return ResponseEntity
                .ok()
                .body(users.toString());
    }
}

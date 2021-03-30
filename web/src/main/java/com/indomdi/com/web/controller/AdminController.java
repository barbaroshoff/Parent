package com.indomdi.com.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.indomdi.com.core.dto.PermissionsDto;
import com.indomdi.com.core.dto.UserEditDto;
import com.indomdi.com.core.dto.UserListDto;
import com.indomdi.com.core.dto.UsersViewDto;
import com.indomdi.com.core.service.AdminService;
import com.indomdi.com.core.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Admin API")
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private RegisterService signupService;

    @Autowired
    private AdminService adminService;

    @PutMapping(path = "/edit/{userName:.*}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Edit user details by username")
    public ResponseEntity<String> edit(@PathVariable("userName") String userName,
                                       @RequestBody UserEditDto userDto) {
        adminService.editAdmin(userName, userDto);
        return ResponseEntity.ok().body("ok");
    }

    @PutMapping(path = "/permissions/{userName:.*}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Edit user details by username")
    public ResponseEntity<String> editPermissions(@PathVariable("userName") String userName,
                                                  @RequestBody PermissionsDto permissionsDto){
        adminService.editPermissions(userName, permissionsDto);
        return ResponseEntity.ok().body("ok");
    }

    @DeleteMapping(value = "/delete/{userName:.*}")
    public ResponseEntity<String> deleteUser(@PathVariable("userName") String userName) {
        signupService.deleteUser
                (userName);

        return ResponseEntity
                .ok()
                .body("ok");
    }

    @GetMapping(path = "/monitoring/{userName:.*}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersViewDto> monitor(@PathVariable("userName") String userName) {
        UsersViewDto monitor = adminService.monitor(userName);


        return ResponseEntity
                .ok()
                .body(monitor);
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get (paginated) users list")
    public ResponseEntity<UserListDto> getUsersList() throws JsonProcessingException {

        UserListDto usersList = adminService.getUsersList();
        return ResponseEntity
                .ok(usersList);
    }
}


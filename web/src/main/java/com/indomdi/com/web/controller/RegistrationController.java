package com.indomdi.com.web.controller;

import com.indomdi.com.core.dto.RegisterResponseDto;
import com.indomdi.com.core.dto.RegisterUserDto;
import com.indomdi.com.core.exception.RegisterUserException;
import com.indomdi.com.core.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.indomdi.com.core.persistent.common.ResponseDTO;


@RestController
@Api(tags = "Signup API")
@RequestMapping(value = "/register")
public class RegistrationController{

    @Autowired
    private RegisterService signupService;

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Signup a new user in Nomeno system")
    public ResponseEntity<ResponseDTO<RegisterResponseDto>> signup(@NonNull  @RequestBody RegisterUserDto user) throws RegisterUserException {
        final RegisterResponseDto result = signupService.signup(user);
        return ResponseEntity
                .ok()
                .body(new ResponseDTO<RegisterResponseDto>().setData(result));
    }

    @GetMapping(path = "/confirm/{secureCode:.*}")
    @ApiOperation(value = "Validate and finish a user signup")
    public String validateSecureCode(@NonNull @PathVariable("secureCode") String secureCode) throws RegisterUserException {
        RegisterResponseDto result = signupService.validateSecureCode(secureCode);

        if (result.getSuccess()) {
            return "validate-signup-success";
        } else {
            return "validate-signup-fail";
        }
    }
}

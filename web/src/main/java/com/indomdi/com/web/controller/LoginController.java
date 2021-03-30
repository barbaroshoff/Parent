package com.indomdi.com.web.controller;


import com.indomdi.com.core.dao.UsersDao;
import com.indomdi.com.core.dto.ForgottenPasswordRequestDto;
import com.indomdi.com.core.dto.ResetPasswordDto;
import com.indomdi.com.core.dto.ResetResponseDto;
import com.indomdi.com.core.persistent.common.ResponseDTO;
import com.indomdi.com.core.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
@Api(tags = "Login API")
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    UsersDao usersDao;

    @GetMapping(path = "/log")
    @ApiOperation(value = "Execute login operation using basic authentication")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred")
    })
    public ResponseEntity<String> login(){
        return  ResponseEntity.ok().body("ok");
    }

    @PostMapping(path = "/reset-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Reset password operation")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred")
    })
    public ResponseEntity<ResponseDTO<ResetResponseDto>> resetPassword(@NonNull @Valid @RequestBody ResetPasswordDto dto) {
        ResetResponseDto response = loginService.resetPassword(dto);
        return ResponseEntity
                .ok()
                .body(new ResponseDTO<ResetResponseDto>().setData(response));
    }

    @PostMapping(path = "/forgotten-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Send email to the user which claim that they forgotten  their password")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred")
    })
    public ResponseEntity<ResponseDTO<Map<String, Object>>> forgottenPassword(@NonNull @Valid @RequestBody ForgottenPasswordRequestDto dto) {

        loginService.forgottenPassword(dto);

        Map<String, Object> map = new HashMap<>();
        map.put("success", Boolean.TRUE);

        return ResponseEntity
                .ok()
                .body(new ResponseDTO<Map<String, Object>>().setData(map));
    }
}

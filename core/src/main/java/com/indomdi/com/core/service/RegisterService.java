package com.indomdi.com.core.service;

import com.indomdi.com.core.dto.RegisterResponseDto;
import com.indomdi.com.core.dto.RegisterUserDto;
import com.indomdi.com.core.exception.RegisterUserException;
import lombok.NonNull;

import javax.validation.Valid;

public interface RegisterService {

    RegisterResponseDto signup(@NonNull @Valid RegisterUserDto userDto) throws RegisterUserException;

    RegisterResponseDto validateSecureCode(@NonNull String secureCode);

    void deleteUser(String userName);
}

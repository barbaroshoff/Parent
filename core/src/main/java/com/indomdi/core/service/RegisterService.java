package com.indomdi.core.service;

import com.indomdi.core.dto.RegisterResponseDto;
import com.indomdi.core.dto.RegisterUserDto;
import com.indomdi.core.exception.RegisterUserException;
import lombok.NonNull;

import javax.validation.Valid;

public interface RegisterService {
    RegisterResponseDto signup(@NonNull @Valid RegisterUserDto userDto) throws RegisterUserException;

    RegisterResponseDto validateSecureCode(@NonNull String secureCode);
}

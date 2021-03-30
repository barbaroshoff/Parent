package com.indomdi.com.core.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class UserListDto {

    @Column(name = "users", nullable = true, length = 25)
    private List<UsersListDto> users;
}

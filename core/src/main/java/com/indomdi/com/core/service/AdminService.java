package com.indomdi.com.core.service;

import com.indomdi.com.core.dto.PermissionsDto;
import com.indomdi.com.core.dto.UserEditDto;
import com.indomdi.com.core.dto.UserListDto;
import com.indomdi.com.core.dto.UsersViewDto;

public interface AdminService {
        void editAdmin(String userName, UserEditDto userDto);
        void editPermissions(String userName, PermissionsDto permissionsDto);
        UsersViewDto monitor(String userName);
        UserListDto getUsersList();
}

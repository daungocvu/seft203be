package com.kms.seft203.service;

import com.kms.seft203.entity.RoleSecurity;
import com.kms.seft203.entity.UserSecurity;

import java.util.List;

public interface SecurityService {
    UserSecurity saveUser(UserSecurity userSecurity);
    RoleSecurity saveRole(RoleSecurity roleSecurity);
    void addRoleToUser(String username, String roleName);
    UserSecurity getUser(String username);
    RoleSecurity getRole(String roleName);
    List<UserSecurity> getUsers();
}

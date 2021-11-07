package com.kms.seft203.config;

import com.kms.seft203.entity.RoleSecurity;
import com.kms.seft203.entity.UserSecurity;
import com.kms.seft203.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * This class is implemented to initialize the data to customize the Spring Security,
 * including the UserSecurity & RoleSecurity
 */
@Component
@RequiredArgsConstructor @Slf4j
public class SecurityDataConfig {
    @Autowired
    private SecurityService securityService;

    public void initSecurityData() {
        try {
            UserSecurity userName = securityService.getUser("name");
            RoleSecurity roleAdmin = securityService.getRole(SecurityConfig.ROLE_ADMIN);
            UserSecurity userOp = securityService.getUser("op");
            RoleSecurity roleUser = securityService.getRole(SecurityConfig.ROLE_USER);

            if (userName == null) {
                securityService.saveUser(new UserSecurity(null, "name", "name", "1", new ArrayList<>()));
            }
            if (userOp == null) {
                securityService.saveUser(new UserSecurity(null, "op", "op", "1", new ArrayList<>()));
            }
            if (roleAdmin == null) {
                securityService.saveRole(new RoleSecurity(null, SecurityConfig.ROLE_ADMIN));
                securityService.addRoleToUser("name", SecurityConfig.ROLE_ADMIN);
            }
            if (roleUser == null) {
                securityService.saveRole(new RoleSecurity(null, SecurityConfig.ROLE_USER));
                securityService.addRoleToUser("op", SecurityConfig.ROLE_USER);
            }
        }
        catch (Exception e) {

            log.error("security error {}", e.getMessage());
        }

    }
}

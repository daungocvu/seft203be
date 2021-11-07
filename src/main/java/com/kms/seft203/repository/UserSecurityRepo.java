package com.kms.seft203.repository;

import com.kms.seft203.entity.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecurityRepo extends JpaRepository<UserSecurity, Integer> {
    UserSecurity findByUsername(String username);
}

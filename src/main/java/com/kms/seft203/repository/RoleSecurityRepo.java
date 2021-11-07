package com.kms.seft203.repository;

import com.kms.seft203.entity.RoleSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleSecurityRepo extends JpaRepository<RoleSecurity, Integer> {

    RoleSecurity findByName(String name);
}

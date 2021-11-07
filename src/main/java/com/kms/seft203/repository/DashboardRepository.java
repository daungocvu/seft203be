package com.kms.seft203.repository;

import com.kms.seft203.entity.Contact;
import com.kms.seft203.entity.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {
    Optional<Dashboard> findByContact(Contact contact);
}

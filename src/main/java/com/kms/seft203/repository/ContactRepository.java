package com.kms.seft203.repository;

import com.kms.seft203.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    @Query("select contact from Contact contact where contact.user.email = ?1")
    Optional<Contact> findByEmail(String email);
}

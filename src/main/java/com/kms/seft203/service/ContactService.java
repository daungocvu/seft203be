package com.kms.seft203.service;

import com.kms.seft203.dto.ContactRequestDto;
import com.kms.seft203.dto.ContactResponseDto;
import com.kms.seft203.exception.EmailNotFoundException;

import java.util.List;

public interface ContactService {
    ContactRequestDto addContact(ContactRequestDto contactRequestDTO) throws EmailNotFoundException;
    List<ContactResponseDto> getAllContact();
}

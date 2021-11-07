package com.kms.seft203.controller;

import com.kms.seft203.dto.ContactRequestDto;
import com.kms.seft203.dto.ContactResponseDto;
import com.kms.seft203.exception.EmailNotFoundException;
import com.kms.seft203.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactApi {
    private static final Logger logger = LoggerFactory.getLogger(ContactApi.class);
    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<List<ContactResponseDto>> getAllContact(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contactService.getAllContact());
    }

    @PostMapping
    public ResponseEntity<ContactRequestDto> addContact(@RequestBody ContactRequestDto contactDtoReq) throws EmailNotFoundException {
        ContactRequestDto contactDTOResponse = contactService.addContact(contactDtoReq);
        return new ResponseEntity<>(contactDTOResponse, HttpStatus.CREATED);
    }

}

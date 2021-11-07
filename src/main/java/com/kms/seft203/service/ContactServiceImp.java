package com.kms.seft203.service;

import com.kms.seft203.dto.ContactRequestDto;
import com.kms.seft203.dto.ContactResponseDto;
import com.kms.seft203.entity.Contact;
import com.kms.seft203.entity.User;
import com.kms.seft203.exception.EmailNotFoundException;
import com.kms.seft203.repository.ContactRepository;
import com.kms.seft203.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImp implements ContactService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ContactResponseDto> getAllContact() {
        List<Contact> listContacts = contactRepository.findAll();
        return listContacts.stream().map(contact -> modelMapper.map(contact, ContactResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ContactRequestDto addContact(ContactRequestDto newContact) throws EmailNotFoundException {
        User user = findByEmail(newContact.getEmail());
        if (user == null) {
            throw new EmailNotFoundException("Email " + newContact.getEmail() + " does not exist");
        }
        Contact contact = modelMapper.map(newContact, Contact.class);
        contact.setDateCreated(LocalDateTime.now());
        contact.setUser(user);

        Contact createContact = contactRepository.save(contact);
        return modelMapper.map(createContact, ContactRequestDto.class);
    }

    public User findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return null;
        }
        return userOptional.get();
    }
}

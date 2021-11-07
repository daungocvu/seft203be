package com.kms.seft203.service;

import com.kms.seft203.dto.ContactRequestDto;
import com.kms.seft203.dto.ContactResponseDto;
import com.kms.seft203.entity.Contact;
import com.kms.seft203.entity.User;
import com.kms.seft203.exception.EmailNotFoundException;
import com.kms.seft203.repository.ContactRepository;
import com.kms.seft203.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
class ContactServiceTest {
    @Autowired
    private ContactService contactService;
    @MockBean
    private ContactRepository contactRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void getAllContactServiceTest() {
        Contact contact = new Contact("Nguyen",
                "A",
                new User(1, "pheonc@gmail.com", "1", "Nguyen A"),
                "Tester",
                "Implement API");
        ContactResponseDto contactResponseDto = new ContactResponseDto("Nguyen Chi",
                "Pheo",
                new User(1, "pheonc@gmail.com", "1", "Nguyen A"),
                "Tester",
                "Implement API");
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        Mockito.when(contactRepository.findAll()).thenReturn(contactList);
        List<ContactResponseDto> contactResponseDtoList = contactService.getAllContact();

        Assert.assertEquals(contactResponseDtoList.get(0).getFirstName(), contact.getFirstName());
        Assert.assertEquals(contactResponseDtoList.get(0).getLastName(), contact.getLastName());
        Assert.assertEquals(contactResponseDtoList.get(0).getTitle(), contact.getTitle());
        Assert.assertEquals(contactResponseDtoList.get(0).getProject(), contact.getProject());
        Assert.assertEquals(contactResponseDtoList.get(0).getUser(), contact.getUser());
    }

    @Test
    void createContactServiceTest() throws EmailNotFoundException {
        User user = new User(1, "b@gmail.com", "2", "Nguyen B");
        Contact contact = new Contact("B", "Nguyen", user, "demo", "demo");
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(java.util.Optional.of(user));

        Mockito.when(contactRepository.save(Mockito.any(Contact.class))).thenReturn(contact);
        ContactRequestDto contactRequestDto = new ContactRequestDto("b@gmail.com", "B", "Nguyen", "demo", "demo");
        ContactRequestDto contactSaveRequestDTO = contactService.addContact(contactRequestDto);
        Assert.assertEquals(contactRequestDto.getFirstName(), contactSaveRequestDTO.getFirstName());
        Assert.assertEquals(contactRequestDto.getLastName(), contactSaveRequestDTO.getLastName());
        Assert.assertEquals(contactRequestDto.getProject(), contactSaveRequestDTO.getProject());
        Assert.assertEquals(contactRequestDto.getTitle(), contactSaveRequestDTO.getTitle());
    }
}

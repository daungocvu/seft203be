package com.kms.seft203.service;

import com.kms.seft203.dto.RegisterRequest;
import com.kms.seft203.entity.User;
import com.kms.seft203.exception.DuplicatedEmailException;
import com.kms.seft203.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testSave_whenSuccess() throws DuplicatedEmailException {
        User user = new User(1,"abc@gmail.com", "1", "A Nguyen");
        RegisterRequest userDto = new RegisterRequest();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setFullName(user.getFullName());

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        RegisterRequest userResponse = userService.save(userDto);
        assertEquals("abc@gmail.com", userResponse.getEmail());
        assertEquals("1", userResponse.getPassword());
        assertEquals("A Nguyen", userResponse.getFullName());
    }
}

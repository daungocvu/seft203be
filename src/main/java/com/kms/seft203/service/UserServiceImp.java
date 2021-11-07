package com.kms.seft203.service;

import com.kms.seft203.dto.RegisterRequest;
import com.kms.seft203.entity.User;
import com.kms.seft203.exception.DuplicatedEmailException;
import com.kms.seft203.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service @Slf4j
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisterRequest save(RegisterRequest userFromReq) throws DuplicatedEmailException {
        String email = userFromReq.getEmail();

        Optional<User> userFromDb = userRepository.findByEmail(email);
        if (userFromDb.isPresent()) {
            throw new DuplicatedEmailException("email is used");
        }
        User user = modelMapper.map(userFromReq, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return modelMapper.map(userRepository.save(user), RegisterRequest.class);
    }
}
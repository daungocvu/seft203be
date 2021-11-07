package com.kms.seft203.service;

import com.kms.seft203.dto.RegisterRequest;
import com.kms.seft203.exception.DuplicatedEmailException;

public interface UserService {
    RegisterRequest save(RegisterRequest userFromReq) throws DuplicatedEmailException;
}
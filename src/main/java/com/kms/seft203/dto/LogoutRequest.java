package com.kms.seft203.dto;

import lombok.Data;

@Data
public class LogoutRequest {
    private String token;
    private String userId;
}

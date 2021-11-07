package com.kms.seft203.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data @NoArgsConstructor @AllArgsConstructor
public class ErrorResponse {

    private HttpStatus status;
    private int statusCode;
    private String message;
}

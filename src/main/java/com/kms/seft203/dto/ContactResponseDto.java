package com.kms.seft203.dto;

import com.kms.seft203.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactResponseDto {
    private String firstName;
    private String lastName;
    private User user;
    private String title;
    private String project;
}

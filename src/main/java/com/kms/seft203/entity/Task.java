package com.kms.seft203.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {
    private String id;
    private String description;
    private Boolean isCompleted;
    private String contactId;
    private LocalDate dateCreated;
}

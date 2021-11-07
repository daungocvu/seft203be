package com.kms.seft203.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class WidgetDto {
    private String type;
    private Integer minWidth;
    private Integer minHeight;
}

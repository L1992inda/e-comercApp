package com.eCommerceApp.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DescriptionDTO {
    private Integer id;
    private String size;
    private String color;
    private Integer quantity;

}

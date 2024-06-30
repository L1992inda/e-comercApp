package com.eCommerceApp.backend.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Integer id;
    private String name;
    private List<ItemDTO> items;

}

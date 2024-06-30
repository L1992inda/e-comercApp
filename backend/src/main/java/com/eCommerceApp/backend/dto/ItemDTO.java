package com.eCommerceApp.backend.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDTO {

    private Integer id;
    private String img;
    @Lob
    private byte[] imageData;
    private String contentType;
    private String name;
    private BigDecimal price;
    private List<DescriptionDTO> descriptions;

    public ItemDTO(Integer id, String img, String name, BigDecimal price, byte[] imageData, String contentType) {
        this.id = id;
        this.img = img;
        this.imageData = imageData;
        this.contentType = contentType;
        this.name = name;
        this.price = price;
    }

}

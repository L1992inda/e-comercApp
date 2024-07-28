package com.eCommerceApp.backend.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String img;
    @NotBlank
    private String name;
    @NotBlank
    private String price;    

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, updatable = false)
    private Category category;

    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    List<Description> descriptions;

    public Item() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Description> getDescriptionList() {
        return descriptions;
    }

    public void setDescriptionList(List<Description> descriptionList) {
        this.descriptions = descriptionList;
    }

}
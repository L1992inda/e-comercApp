package com.eCommerceApp.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerceApp.backend.dto.CategoryDTO;
import com.eCommerceApp.backend.service.CategoryUserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/category/user")
public class CategoryUserController {

    private final CategoryUserService categoryUserService;

    public CategoryUserController(CategoryUserService categoryUserService) {
        this.categoryUserService = categoryUserService;
    }

    @GetMapping(path = "/categories")
    public ResponseEntity< List<CategoryDTO>>getCategories(@RequestParam(required = false) Integer ID) {
        List<CategoryDTO> allCategoryList = categoryUserService.getObjectWithRelations(ID);
        return ResponseEntity.ok(allCategoryList);
    }
}

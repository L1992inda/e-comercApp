package com.eCommerceApp.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerceApp.backend.dto.CategoryDTO;
import com.eCommerceApp.backend.service.CategoryAdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/category/admin")
public class CategoryAdminController {

    private final CategoryAdminService categoryAdminService;

    public CategoryAdminController(CategoryAdminService categoryAdminService) {
        this.categoryAdminService = categoryAdminService;

    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> add(@Valid @RequestBody CategoryDTO cDto) {
        String result = categoryAdminService.addToDB(cDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteFieldWithRelations(@PathVariable("id") Integer ID) {
        String result = categoryAdminService.deleteFieldAndRelations(ID);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/delete/all")
    public ResponseEntity<String> deleteAll() {
        String result = categoryAdminService.deleteAll();
        return ResponseEntity.ok(result);
    }
}

package com.eCommerceApp.backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerceApp.backend.dto.DescriptionDTO;
import com.eCommerceApp.backend.service.DescriptionAdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/description/admin")
public class DescriptionAdminController {
    
    private final  DescriptionAdminService descriptionAdminService;

    public DescriptionAdminController(DescriptionAdminService descriptionAdminService) {
        this.descriptionAdminService = descriptionAdminService;
    }

    @PostMapping(path = "/add/{id}")
    public ResponseEntity<String> add(@Valid @RequestBody DescriptionDTO dDto, @PathVariable("id") Integer ID) {
        String result = descriptionAdminService.save(dDto, ID);
        return ResponseEntity.ok(result);

    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer ID, @RequestBody Map<String, String> update) {
        String result = descriptionAdminService.updateField(ID, update);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteFieldWithRelations(@PathVariable("id") Integer ID) {
        String result = descriptionAdminService.deleteFieldAndRelations(ID);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/delete/all")
    public ResponseEntity<String> deleteAll() {
        String result = descriptionAdminService.deleteAll();
        return ResponseEntity.ok(result);
    }
}

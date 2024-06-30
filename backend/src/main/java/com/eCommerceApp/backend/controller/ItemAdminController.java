package com.eCommerceApp.backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eCommerceApp.backend.service.ItemAdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/item/admin")
public class ItemAdminController {

    private final ItemAdminService itemAdminService;
    private final String path = "src/main/resources/img/";


    public ItemAdminController(ItemAdminService itemAdminService) {
        this.itemAdminService = itemAdminService;
    }


    @PostMapping(path = "/add/{id}")
    public ResponseEntity<String> add(@Valid @RequestParam(value = "name") String name,
            @RequestParam(value = "price") String price, @PathVariable("id") Integer ID,
            @RequestParam(value = "file") MultipartFile file) throws IOException {

        String filePath = path + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        String response = itemAdminService.save(ID, name, price, filePath);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer ID, @RequestBody Map<String, String> update) {
        String response = itemAdminService.updateField(ID, update);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteFieldWithRelations(@PathVariable("id") Integer ID) {
        String result = itemAdminService.deleteFieldAndRelations(ID);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/delete/all")
    public ResponseEntity<String> deleteAll() {
        String result = itemAdminService.deleteAll();
        return ResponseEntity.ok(result);
    }
}

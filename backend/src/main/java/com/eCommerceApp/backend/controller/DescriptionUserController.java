package com.eCommerceApp.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerceApp.backend.model.Description;
import com.eCommerceApp.backend.service.DescriptionUserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/description/user")
public class DescriptionUserController {

    private final DescriptionUserService descriptionUserService;

    public DescriptionUserController(DescriptionUserService descriptionUserService) {
        this.descriptionUserService = descriptionUserService;
    }

    @GetMapping(path = "/descriptions")
    public ResponseEntity<List<Description>> get(@RequestParam(required = false) Integer ID) {
        List<Description> descriptionList = descriptionUserService.getDescriptionByID(ID);
        return ResponseEntity.ok(descriptionList);
    }

    @GetMapping(path = "/totalQuantity/{id}")
    public ResponseEntity <Integer> getTotalQuantity(@PathVariable("id") Integer id) {
        Integer total = descriptionUserService.getTotalQuantity(id);
        return ResponseEntity.ok(total);
    }

}

package com.eCommerceApp.backend.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerceApp.backend.dto.ItemDTO;
import com.eCommerceApp.backend.service.ItemUserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/item/user")
public class ItemUserController {

    private final ItemUserService itemUserService;

    public ItemUserController(ItemUserService itemUserService) {
        this.itemUserService = itemUserService;
    }

    @GetMapping(path = "/items")
    public ResponseEntity<List<ItemDTO>> getItems(@RequestParam(required = false) Integer ID) {
        List<ItemDTO> itemList = itemUserService.getObjectWithRelations(ID);
   
        return ResponseEntity.ok(itemList);
    }

}

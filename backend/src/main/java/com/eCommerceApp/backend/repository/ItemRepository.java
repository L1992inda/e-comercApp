package com.eCommerceApp.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eCommerceApp.backend.model.Item;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    

}

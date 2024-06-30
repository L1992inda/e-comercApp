package com.eCommerceApp.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eCommerceApp.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findByName(String name);


}

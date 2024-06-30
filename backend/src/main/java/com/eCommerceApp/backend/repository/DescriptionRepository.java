package com.eCommerceApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eCommerceApp.backend.model.Description;


public interface DescriptionRepository extends JpaRepository<Description, Integer> {

 @Query("SELECT SUM(d.quantity) FROM Description d WHERE d.item.id = :ID")
   Integer totalQuantity(@Param("ID") Integer ID);
}

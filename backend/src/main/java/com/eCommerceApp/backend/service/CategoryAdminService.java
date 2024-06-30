package com.eCommerceApp.backend.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.eCommerceApp.backend.dto.CategoryDTO;
import com.eCommerceApp.backend.exceptions.CouldNotSaveException;
import com.eCommerceApp.backend.exceptions.FailedDeleteExeption;
import com.eCommerceApp.backend.model.Category;
import com.eCommerceApp.backend.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryAdminService extends AdminService<CategoryDTO> {

    private final CategoryRepository categoryRepository;

    public CategoryAdminService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String addToDB(CategoryDTO cDto) {

        if (nemeFieldCheck(cDto.getName()) == true) {
            return "Field with name : " + cDto.getName() + " alredy exist";
        }

        try {
            Category category = new Category();
            category.setName(cDto.getName());
            categoryRepository.save(category);
            return "saved successfully";
        } catch (DataAccessException e) {
            throw new CouldNotSaveException("Could not save to DB:" + cDto + e.getMessage());
        }

    }

    @Override
    public String deleteFieldAndRelations(Integer ID) {

        Category fieldToDelete = categoryRepository.findById(ID)
                .orElseThrow(() -> new EntityNotFoundException("Could not find Category with ID: " + ID));

        try {
            categoryRepository.delete(fieldToDelete);
            return "Deleted field with ID: " + ID;
        } catch (DataAccessException e) {
            throw new FailedDeleteExeption("Could not delete Category with ID: " + ID + " " + e.getMessage());
        }

    };

    @Override
    public String deleteAll() {
        try {
            categoryRepository.deleteAll();
            return "deleted all fields!";
        } catch (DataAccessException e) {
            throw new FailedDeleteExeption("Couldn't delete all " + e.getMessage());
        }
    }

    private boolean nemeFieldCheck(String name) {
        boolean existInDB = false;

        Category category = categoryRepository.findByName(name);

        if (category != null) {
            existInDB = true;
        }
        return existInDB;
    }
}

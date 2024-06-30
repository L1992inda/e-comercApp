package com.eCommerceApp.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.eCommerceApp.backend.dto.CategoryDTO;
import com.eCommerceApp.backend.dto.DescriptionDTO;
import com.eCommerceApp.backend.dto.ItemDTO;
import com.eCommerceApp.backend.model.Category;
import com.eCommerceApp.backend.model.Description;
import com.eCommerceApp.backend.model.Item;
import com.eCommerceApp.backend.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryUserService extends UserService<CategoryDTO> {

    private final CategoryRepository categoryRepository;

    public CategoryUserService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getObjectWithRelations(Integer ID) {
        try {

            List<Category> categories = categoryRepository.findAll();
            List<CategoryDTO> categoriesDTOs = new ArrayList<>();

            if (ID == null) {
                categories = categoryRepository.findAll();
            } else {

                Category category = categoryRepository.findById(ID)
                        .orElseThrow(() -> new EntityNotFoundException("Could not find Category with ID: " + ID));
                categories = Collections.singletonList(category);
            }

            for (Category c : categories) {
                CategoryDTO cDTO = new CategoryDTO();
                cDTO.setId(c.getId());
                cDTO.setName(c.getName());

                List<ItemDTO> itemsDTOs = new ArrayList<>();
                for (Item i : c.getItemList()) {

                    ItemDTO iDTO = new ItemDTO();

                    Path filePath = Paths.get(i.getImg());
                    byte[] fileBytes = Files.readAllBytes(filePath);
                    String contentType = Files.probeContentType(filePath);

                    iDTO.setId(i.getId());
                    iDTO.setImg(i.getImg());
                    iDTO.setImageData(fileBytes);
                    iDTO.setContentType(contentType);
                    iDTO.setName(i.getName());
                    iDTO.setPrice(convertStringToBigDecimal(i.getPrice()));
                    List<DescriptionDTO> descriptionsList = new ArrayList<>();
                    for (Description d : i.getDescriptionList()) {
                        DescriptionDTO newDescription = new DescriptionDTO();
                        newDescription.setId(d.getId());
                        newDescription.setColor(d.getColor());
                        newDescription.setQuantity(d.getQuantity());
                        newDescription.setSize(d.getSize());
                        descriptionsList.add(newDescription);
                    }
                    iDTO.setDescriptions(descriptionsList);

                    itemsDTOs.add(iDTO);
                }
                cDTO.setItems(itemsDTOs);
                categoriesDTOs.add(cDTO);
            }
            return categoriesDTOs;
        } catch (DataAccessException | IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}

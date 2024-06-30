package com.eCommerceApp.backend.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.eCommerceApp.backend.dto.ItemDTO;
import com.eCommerceApp.backend.exceptions.CouldNotSaveException;
import com.eCommerceApp.backend.model.Category;
import com.eCommerceApp.backend.model.Item;
import com.eCommerceApp.backend.repository.CategoryRepository;
import com.eCommerceApp.backend.repository.ItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemAdminService extends AdminService<ItemDTO> {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public ItemAdminService(ItemRepository itemRepository, CategoryRepository categoryRepository) {

        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;

    }

    @Override
    public String save(Integer ID, String name, String price, String filePath) {

        Category category = categoryRepository.findById(ID)
                .orElseThrow(() -> new EntityNotFoundException("Could not find Category with ID: " + ID));
        try {
            Item newItem = new Item();
            newItem.setImg(filePath);
            newItem.setName(name);
            newItem.setPrice(format(price));
            newItem.setCategory(category);
            itemRepository.save(newItem);
            return "saved successfully!";
        } catch (DataAccessException e) {
            throw new CouldNotSaveException("Could not save to DB: " + ID + name + price + filePath + e.getMessage());

        }
    }

    @Override
    public String updateField(Integer ID, Map<String, String> item) {
        Item fieldToUpdate = itemRepository.findById(ID)
                .orElseThrow(() -> new EntityNotFoundException("Could not find Item with ID: " + ID));

        item.forEach((key, value) -> {
            switch (key) {
                case "img":
                    fieldToUpdate.setImg(value);
                    break;
                case "name":
                    fieldToUpdate.setName(value);
                    break;
                case "price":
                    fieldToUpdate.setPrice(format(value));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + value);
            }
        });

        itemRepository.save(fieldToUpdate);
        return "Item with ID " + ID + " updated successfully.";

    }

    @Override
    public String deleteFieldAndRelations(Integer ID) {
        Item fieldToDelete = itemRepository.findById(ID)
                .orElseThrow(() -> new EntityNotFoundException("Could not find Item with ID: " + ID));

        itemRepository.delete(fieldToDelete);
        return "Deleted field with ID: " + ID;

    }

    @Override
    public String deleteAll() {
        itemRepository.deleteAll();
        return "deleted all fields!";
    };

    private String format(String price) {
        String checkPrice = price;

        if (!price.contains(".")) {
            checkPrice = price.concat(".");
        }
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        BigDecimal bigDecimal = new BigDecimal(checkPrice);
        return decimalFormat.format(bigDecimal);

    }

}

package com.eCommerceApp.backend.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.eCommerceApp.backend.dto.DescriptionDTO;
import com.eCommerceApp.backend.exceptions.CouldNotSaveException;
import com.eCommerceApp.backend.exceptions.FailedDeleteExeption;
import com.eCommerceApp.backend.model.Description;
import com.eCommerceApp.backend.model.Item;
import com.eCommerceApp.backend.repository.DescriptionRepository;
import com.eCommerceApp.backend.repository.ItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DescriptionAdminService extends AdminService<DescriptionDTO> {

    private final DescriptionRepository descriptionRepository;
    private final ItemRepository itemRepository;

    public DescriptionAdminService(DescriptionRepository descriptionRepository, ItemRepository itemRepository) {
        this.descriptionRepository = descriptionRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public String save(DescriptionDTO descriptionDTO, Integer ID) {

        Item item = itemRepository.findById(ID)
                .orElseThrow(() -> new EntityNotFoundException("Could not find Item with ID: " + ID));
        if (sizeFieldCheck(descriptionDTO.getSize()) == false) {
            return "invalid size input " + descriptionDTO.getSize();
        }
        try {
            Description newItemDescription = new Description();
            newItemDescription.setColor(removeWhiteSpace(descriptionDTO.getColor()));
            newItemDescription.setSize(descriptionDTO.getSize().toUpperCase());
            newItemDescription.setQuantity(descriptionDTO.getQuantity());
            newItemDescription.setItem(item);

            descriptionRepository.save(newItemDescription);

            return "saved successfully with ID : " + ID;
        } catch (DataAccessException e) {
            throw new CouldNotSaveException("Could not save to DB " + e.getMessage());
        }
    }

    @Override
    public String updateField(Integer ID, Map<String, String> description) {

        Description fieldToUpdate = descriptionRepository.findById(ID)
                .orElseThrow(() -> new EntityNotFoundException("Could not find Description with ID: " + ID));

        description.forEach((key, value) -> {
            switch (key) {
                case "quantity":
                    fieldToUpdate.setQuantity(Integer.parseInt(value));
                    break;

                case "size":
                    if (!sizeFieldCheck(value)) {
                        throw new IllegalArgumentException("Invalid size input: " + value);
                    }
                    fieldToUpdate.setSize(value);
                    break;

                case "color":
                    fieldToUpdate.setColor(removeWhiteSpace(value));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + value);
            }
        });

        descriptionRepository.save(fieldToUpdate);
        return "Description with ID " + ID + " updated successfully.";
    }

    @Override
    public String deleteFieldAndRelations(Integer ID) {

        Description fieldToDelete = descriptionRepository.findById(ID)
                .orElseThrow(() -> new EntityNotFoundException("Could not find Description with ID: " + ID));
        try {
            descriptionRepository.delete(fieldToDelete);
            return "Deleted field with ID: " + ID;
        } catch (DataAccessException e) {
            throw new FailedDeleteExeption("Could not delete Description with ID: " + ID + " " + e.getMessage());
        }
    }

    @Override
    public String deleteAll() {
        descriptionRepository.deleteAll();
        return "deleted all fields!";
    };

    private boolean sizeFieldCheck(String input) {
        String[] sizes = { "S", "M", "L" };
        boolean validSize = false;
        for (String size : sizes) {
            if (input.equalsIgnoreCase(size)) {
                validSize = true;
                break;
            }
        }
        return validSize;
    }
    private String removeWhiteSpace(String color){
        return color.replaceAll("\\s","");
    }

}

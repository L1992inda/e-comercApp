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

import com.eCommerceApp.backend.dto.DescriptionDTO;
import com.eCommerceApp.backend.dto.ItemDTO;
import com.eCommerceApp.backend.model.Description;
import com.eCommerceApp.backend.model.Item;
import com.eCommerceApp.backend.repository.ItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemUserService extends UserService<ItemDTO> {

    private final ItemRepository itemRepository;

    public ItemUserService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDTO> getObjectWithRelations(Integer ID) {
        try {
            List<Item> items = itemRepository.findAll();
            List<ItemDTO> itemDTOs = new ArrayList<>();

            if (ID == null) {
                items = itemRepository.findAll();
            } else {

                Item item = itemRepository.findById(ID)
                        .orElseThrow(() -> new EntityNotFoundException("Could not find Category with ID: " + ID));
                items = Collections.singletonList(item);
            }

            for (Item i : items) {
                ItemDTO iDTO = new ItemDTO();
                iDTO.setId(i.getId());
                iDTO.setImg(i.getImg());
                iDTO.setImageData(getBytes(i.getImg()));
                iDTO.setContentType(getContentType(i.getImg()));
                iDTO.setName(i.getName());
                iDTO.setPrice(convertStringToBigDecimal(i.getPrice()));

                List<DescriptionDTO> descriptionDTOs = new ArrayList<>();

                for (Description d : i.getDescriptionList()) {
                    DescriptionDTO dDTO = new DescriptionDTO();
                    dDTO.setId(d.getId());
                    dDTO.setSize(d.getSize());
                    dDTO.setColor(d.getColor());
                    dDTO.setQuantity(d.getQuantity());

                    descriptionDTOs.add(dDTO);
                }
                iDTO.setDescriptions(descriptionDTOs);
                itemDTOs.add(iDTO);
            }
            return itemDTOs;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private byte[] getBytes(String imgPath) {
        byte[] fileBytes = null;
        try {
            Path filePath = Paths.get(imgPath);
            fileBytes = Files.readAllBytes(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileBytes;
    }

    private String getContentType(String imgPath) {

        String contentType = null;
        try {
            Path filePath = Paths.get(imgPath);
            contentType = Files.probeContentType(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentType;
    }

}

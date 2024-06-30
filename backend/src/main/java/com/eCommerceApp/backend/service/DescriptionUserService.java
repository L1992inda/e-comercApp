package com.eCommerceApp.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.eCommerceApp.backend.model.Description;
import com.eCommerceApp.backend.repository.DescriptionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DescriptionUserService extends UserService<Description> {

    private final DescriptionRepository descriptionRepository;

    public DescriptionUserService(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    @Override
    public List<Description> getObjectWithRelations(Integer ID) {
        return Collections.emptyList();
    }

    public List<Description> getDescriptionByID(Integer ID) {
        try {
            List<Description> allDescriptionsList = new ArrayList<>();
            List<Description> descriptionList = new ArrayList<>();

            Description description = new Description();

            if (ID == null) {
                allDescriptionsList = descriptionRepository.findAll();
            } else {
                description = descriptionRepository.findById(ID)
                        .orElseThrow(() -> new EntityNotFoundException("Could not find Category with ID: " + ID));
                descriptionList = Collections.singletonList(description);
            }
            for (Description d : allDescriptionsList) {
                Description newDescription = new Description();
                newDescription.setId(d.getId());
                newDescription.setColor(d.getColor());
                newDescription.setSize(d.getSize());
                newDescription.setQuantity(d.getQuantity());

                descriptionList.add(newDescription);
            }
            return descriptionList;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

   public Integer getTotalQuantity(Integer ID){
    return descriptionRepository.totalQuantity(ID);
   }
}

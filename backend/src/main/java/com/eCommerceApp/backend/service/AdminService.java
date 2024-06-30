package com.eCommerceApp.backend.service;

import java.util.Map;

public abstract class AdminService<T> {

    public String addToDB(T t) {
        return "saved successfully: " + t;
    };

    public String save(T t, Integer ID) {
        return "saved successfully with ID : " + ID;

    };
    public String save(Integer ID,String name, String price, String filePath) {
        return "saved successfully with ID : " + ID;

    };


    public String updateField(Integer ID) {
        return "Description with ID " + ID + " updated successfully.";
    };

    public String deleteFieldAndRelations(Integer ID) {
        return "Deleted field with ID: " + ID;
    };

    public String deleteAll() {
        return "deleted all fields!";

    };

    public String updateField(Integer ID, Map<String, String> map) {
        return "Item with ID " + ID + " updated successfully.";
    };

}

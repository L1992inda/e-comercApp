package com.eCommerceApp.backend.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public abstract class UserService<T> {

    public List<T> getObjectWithRelations(Integer ID){
        List<T> list =  new ArrayList<>();
        return list;
    };

    public BigDecimal convertStringToBigDecimal(String price){

        BigDecimal bigDecimalPrice = new BigDecimal(price);
        return bigDecimalPrice;

    }

}

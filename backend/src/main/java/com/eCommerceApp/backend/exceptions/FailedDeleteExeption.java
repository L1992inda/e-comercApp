package com.eCommerceApp.backend.exceptions;

import org.springframework.dao.DataAccessException;

public class FailedDeleteExeption extends DataAccessException {

    public FailedDeleteExeption(String msg){
        super(msg);
    }
    
}

package com.eCommerceApp.backend.exceptions;

import org.springframework.dao.DataAccessException;

public class CouldNotSaveException extends DataAccessException {

    public CouldNotSaveException(String msg){
        super(msg);
    }

}

package com.talita.crud_spring.exception;

import java.io.Serial;

public class RecordNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id){
        super("Record not found with id: " + id);
    }

}

package com.talita.crud_spring.domain.enums;

public enum Status {
    ACTIVE("Ativo"), INACTIVE("Inativo");

    private final String value;

    Status(String value){
        this.value = value;

    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}

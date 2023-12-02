package com.utaha.pojo;

import lombok.Data;

@Data
public class Pet {

    private int id;

    private String name;

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

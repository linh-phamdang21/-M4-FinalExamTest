package com.codegym.demo.model;

import javax.persistence.*;

@Entity
@Table
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;

    public ProductType(){

    }

    public ProductType(Long Id, String name){
        this.Id = Id;
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

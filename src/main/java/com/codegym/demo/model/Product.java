package com.codegym.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private String price;
    private Timestamp createAt;
    private String brand;
    private String description;

    @ManyToOne
    private ProductType type;

    public Product(){

    }

    public Product(String name, String price, Timestamp createAt, String brand, String description, ProductType type){
        this.name = name;
        this.price = price;
        this.createAt = createAt;
        this.brand = brand;
        this.description = description;
        this.type = type;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductType getType(){
        return type;
    }

    public void setType(ProductType type){
        this.type = type;
    }
}

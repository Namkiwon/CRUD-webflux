package com.example.CRUDwebflux.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "products")
public class Product {

@Id

private String id;

@NotBlank
@Size(max = 50)
private String name;

@NotNull
private String shape;

    public Product(String id , String name, String shape){
        this.id = id;
        this.name  = name;
        this.shape = shape;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}

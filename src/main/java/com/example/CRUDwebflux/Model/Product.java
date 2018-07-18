package com.example.CRUDwebflux.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Document(collection = "products")
public class Product {

@NotBlank
@Size(max = 50)
private String name;

@NotNull
private String shape;


    public Product(){}
    public Product(String name, String shape){

        this.name  = name;
        this.shape = shape;
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

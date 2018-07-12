package com.example.CRUDwebflux.Controller;

import com.example.CRUDwebflux.Model.Product;
import com.example.CRUDwebflux.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;
import com.google.gson.*;

@RestController
public class ProductController {

    @Value("${spring.data.mongodb.uri}") private String dburl;
    @Value("${MongodbUrl}") private String url;


    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public Flux<Product> getAllProducts(){
        System.out.println(url+"1212");


        return productRepository.findAll();
    }

    @GetMapping("/products/{productId}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable(value = "id") String productId){
        return productRepository.findById(productId).map(savedProduct -> ResponseEntity.ok(savedProduct)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/products")
    public Mono<Product> createProduct(@Valid @RequestBody JsonObject productJson) {
        System.out.println("asdfasdf");

        System.out.println(productJson.get("name").getAsString());
//        String id = productJson.get("id").getAsString();
//        String name = productJson.get("name").getAsString();
//        String shape = productJson.get("shape").getAsString();
        Product product = new Product("asdf","asdf","asdfadf");
        return productRepository.save(product);
    }


    @PutMapping("/products/{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable(value = "id") String productId,
                                                   @Valid @RequestBody Product product) {
        return productRepository.findById(productId)
                .flatMap(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setShape(product.getShape());
                    return productRepository.save(existingProduct);
                })
                .map(updatedTweet -> new ResponseEntity<>(updatedTweet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}

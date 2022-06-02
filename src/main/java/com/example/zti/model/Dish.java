package com.example.zti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "menu")
public class Dish {
    @Id
    private String id;
    private String name;
    private Double price;

    public Dish(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}

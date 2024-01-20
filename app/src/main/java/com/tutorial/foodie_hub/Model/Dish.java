package com.tutorial.foodie_hub.Model;

import java.io.Serializable;

public class Dish implements Serializable {
    private String name;
    private String description;
    private double price;
    private double quantity;
    private String id;

    public Dish(String dishName, String dishDescription, double dishPrice) {
        this.name = dishName;
        this.description = dishDescription;
        this.price = dishPrice;
    }


    @Override
    public String toString() {
        return "Dish Details:\n" +
                "Name: " + name + "\n" +
                "Description: " + description + "\n" +
                "Price: $" + price + "\n" +
                "Quantity: " + quantity+"\n"+
                "***************************";
    }

    public Dish(String name, String description, double price, double quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Dish() {
    }

    public Dish(String name, String description, double price, double quantity, String id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

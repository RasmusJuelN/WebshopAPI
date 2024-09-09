package com.example.webshopapi.dto;

public class ProductDto {

    private int id;
    private String name;
    private double price;
    private int stock;
    private String description;
    private String imageBase64;  // Base64-encoded string for the image

    // Constructor with all fields
    public ProductDto(int id, String name, double price, int stock, String description, String imageBase64) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.imageBase64 = imageBase64;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    // Default constructor
    public ProductDto() {
    }

    // Getters and setters for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}

package com.example.webshopapi.controllers;

import com.example.webshopapi.dto.ProductDto;
import org.springframework.web.bind.annotation.*;
import com.example.webshopapi.models.Product;
import com.example.webshopapi.services.ProductService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

// Controller handles http requests and returns responses
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ProductDto createProduct(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("stock") int stock,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) throws IOException {
        return productService.createProduct(name, price, stock, description, image);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ProductDto updateProduct(
            @PathVariable int id,
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("stock") int stock,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) throws IOException {
        return productService.updateProduct(id, name, price, stock, description, image);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}

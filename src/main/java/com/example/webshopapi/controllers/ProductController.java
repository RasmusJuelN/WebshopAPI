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
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    //TODO: on front end encode the image as a Base64 string and include it in the JSON payload.


    @PostMapping
    public Product createProduct(@RequestParam("name") String name,
                                 @RequestParam("price") double price,
                                 @RequestParam("stock") int stock,
                                 @RequestParam("description") String description,
                                 @RequestParam("image") MultipartFile image) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setDescription(description);
        product.setImage(image.getBytes());
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")

         public Product updateProduct(@PathVariable int id,
                                         @RequestParam("name") String name,
                                         @RequestParam("price") double price,
                                         @RequestParam("stock") int stock,
                                         @RequestParam("description") String description,
                                         @RequestParam("image") MultipartFile image) throws IOException {
                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                product.setStock(stock);
                product.setDescription(description);
                product.setImage(image.getBytes());
                return productService.updateProduct(id, product);
            }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}

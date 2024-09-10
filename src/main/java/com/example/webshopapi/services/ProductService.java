
package com.example.webshopapi.services;

import com.example.webshopapi.dto.ProductDto;
import com.example.webshopapi.models.Product;
import com.example.webshopapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

// The Service layer is responsible for handling business logic and
// communication between the controller and the repository
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getStock(),
                        product.getDescription(),

                        Base64.getEncoder().encodeToString(product.getImage())))  // Convert image to Base64 string
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getDescription(),
                Base64.getEncoder().encodeToString(product.getImage()));
    }

    public ProductDto createProduct(String name, double price, int stock, String description, MultipartFile image) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setDescription(description);
        product.setImage(image.getBytes());
        Product savedProduct = productRepository.save(product);
        return new ProductDto(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getStock(),
                savedProduct.getDescription(),
                Base64.getEncoder().encodeToString(savedProduct.getImage()));
    }

    public ProductDto updateProduct(int id, String name, double price, int stock, String description, MultipartFile image) throws IOException {
        if (productRepository.existsById(id)) {
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setDescription(description);
            product.setImage(image.getBytes());
            Product updatedProduct = productRepository.save(product);
            return new ProductDto(
                    updatedProduct.getId(),
                    updatedProduct.getName(),
                    updatedProduct.getPrice(),
                    updatedProduct.getStock(),
                    updatedProduct.getDescription(),
                    Base64.getEncoder().encodeToString(updatedProduct.getImage()));
        }
        return null;
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }}

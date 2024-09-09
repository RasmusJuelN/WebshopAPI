
package com.example.webshopapi.services;

import com.example.webshopapi.dto.ProductDto;
import com.example.webshopapi.models.Product;
import com.example.webshopapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

// The Service layer is responsible for handling business logic and
// communication between the controller and the repository
@Service
public class ProductService {
    //The ProductRepository is injected into the service using the @Autowired annotation, allowing the service to use the repository's methods
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

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}

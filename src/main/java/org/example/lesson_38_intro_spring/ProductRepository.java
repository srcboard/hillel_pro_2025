package org.example.lesson_38_intro_spring;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final List<ProductDto> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new ProductDto(1, "Apple", 10.0));
        products.add(new ProductDto(2, "Banana", 15.5));
        products.add(new ProductDto(3, "Cherry", 7.25));
    }

    public void addProduct(ProductDto p) {
        products.add(p);
    }

    public ProductDto getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst().orElse(null);
    }

    public boolean updateProduct(int id, String newName, double newPrice) {
        ProductDto p = getProductById(id);
        if (p != null) {
            p.setName(newName);
            p.setPrice(newPrice);
            return true;
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        return products.removeIf(p -> p.getId() == id);
    }

    public List<ProductDto> getAllProducts() {
        return products;
    }
}

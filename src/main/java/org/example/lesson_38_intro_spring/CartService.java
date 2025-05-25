package org.example.lesson_38_intro_spring;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private final List<ProductDto> items = new ArrayList<>();
    private final ProductRepository repository;

    public CartService(ProductRepository repository) {
        this.repository = repository;
    }

    public boolean addProductById(int id) {
        ProductDto p = repository.getProductById(id);
        if (p != null) {
            items.add(p);
            return true;
        }
        return false;
    }

    public boolean removeProductById(int id) {
        return items.removeIf(p -> p.getId() == id);
    }

    public List<ProductDto> getItems() {
        return items;
    }
}

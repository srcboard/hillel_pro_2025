package org.example.lesson5;

// Клас розрахунку базової вартості товару
// ЗАВДАННЯ: Виправити код класу.
public class CalcCostBase {

    // Базовий розрахунок вартості товару
    public double calcCost(Product product) {
        return product.getQuota() * product.getPrice();
    }
}

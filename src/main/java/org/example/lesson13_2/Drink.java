package org.example.lesson13_2;

public enum Drink {
    COCKTAIL("Cocktail"),
    COFFEE("Coffee"),
    TEA("Tea"),
    JUICE("Juice");

    private final String name;

    Drink(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

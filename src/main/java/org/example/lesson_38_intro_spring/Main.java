package org.example.lesson_38_intro_spring;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ProductRepository repo = new ProductRepository();
            CartService cartService = new CartService(repo);

            System.out.println("Available products:");
            for (ProductDto p : repo.getAllProducts()) {
                System.out.println(p);
            }
            System.out.println();

            while (true) {
                System.out.println("\nAvailable commands: add <id>, remove <id>, view, exit");
                System.out.print("Command: ");
                String line = scanner.nextLine().trim();
                if (line.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the program.");
                    break;
                } else if (line.startsWith("add ")) {
                    try {
                        int id = Integer.parseInt(line.split(" ")[1]);
                        if (cartService.addProductById(id)) {
                            System.out.println("Product with id=" + id + " added to cart.");
                        } else {
                            System.out.println("Product with id=" + id + " not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid add command format. Use: add <id>");
                    }
                } else if (line.startsWith("remove ")) {
                    try {
                        int id = Integer.parseInt(line.split(" ")[1]);
                        if (cartService.removeProductById(id)) {
                            System.out.println("Product with id=" + id + " removed from cart.");
                        } else {
                            System.out.println("Product with id=" + id + " is not in the cart.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid remove command format. Use: remove <id>");
                    }
                } else if (line.equalsIgnoreCase("view")) {
                    System.out.println("Cart contents:");
                    for (ProductDto p : cartService.getItems()) {
                        System.out.println(p);
                    }
                    if (cartService.getItems().isEmpty()) {
                        System.out.println("(cart is empty)");
                    }
                } else {
                    System.out.println("Unknown command.");
                }
            }
        }
    }
}

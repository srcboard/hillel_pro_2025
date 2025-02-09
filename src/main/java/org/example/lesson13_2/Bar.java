package org.example.lesson13_2;

import java.util.Queue;
import java.util.LinkedList;

class Bar {
    private final Queue<Order> orders = new LinkedList<>();
    private final int clientsCount;
    private int servedClientsCount = 0;

    public Bar(int clientsCount) {
        this.clientsCount = clientsCount;
    }

    public synchronized void placeOrder(Order order) {
        orders.add(order);
        System.out.println(Thread.currentThread().getName() + " ordered: " + order.drink());
        notify();
    }

    private Drink prepareDrink() {
        Order order = orders.poll();
        if (order != null) {
            return order.drink();
        } else {
            return null;
        }
    }

    private void serveDrink() {
        servedClientsCount++;
    }

    public synchronized void receiveAndProcessOrder() {
        while (orders.isEmpty() && isOpen()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Drink drink = prepareDrink();
        if (drink != null) {
            serveDrink();
            System.out.println(Thread.currentThread().getName() + " has prepared and served: " + drink);
        }
    }

    public synchronized boolean isOpen() {
        return clientsCount > servedClientsCount;
    }
}

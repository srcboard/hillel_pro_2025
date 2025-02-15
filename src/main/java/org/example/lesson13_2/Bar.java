package org.example.lesson13_2;

import java.util.Optional;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Bar {
    private final Queue<Order> orders = new LinkedList<>();
    private final int clientsCount;
    private int servedClientsCount = 0;
    private final Lock lock = new ReentrantLock();

    public Bar(int clientsCount) {
        this.clientsCount = clientsCount;
    }

    public void placeOrder(Order order) {
        lock.lock();
        try {
            orders.add(order);
            System.out.println(Thread.currentThread().getName() + " ordered: " + order.drink());
        } finally {
            lock.unlock();
        }
    }

    private Optional<Drink> prepareDrink() {
        return Optional.ofNullable(orders.poll()).map(Order::drink);
    }

    private void serveDrink() {
        servedClientsCount++;
    }

    public boolean receiveAndProcessOrder() {
        if (lock.tryLock()) {
            try {
                Optional<Drink> drink = prepareDrink();
                drink.ifPresent(d -> {
                    serveDrink();
                    System.out.println(Thread.currentThread().getName() + " has prepared and served: " + d);
                });
                return isOpen();
            } finally {
                lock.unlock();
            }
        } else {
            return true;
        }
    }

    private boolean isOpen() {
        return clientsCount > servedClientsCount;
    }
}

package org.example.lesson_32_logging_debugging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CoffeeOrderBoard {
    private static final Logger logger = LoggerFactory.getLogger(CoffeeOrderBoard.class);

    private final List<Order> orders = new LinkedList<>();
    private int nextOrderNumber = 1;

    public void add(String customerName) {
        Order order = new Order(nextOrderNumber++, customerName);
        orders.add(order);
        logger.info("Order added: #{} for {}", order.orderNumber(), customerName);
    }

    public Order deliver() {
        if (orders.isEmpty()) {
            logger.warn("Attempted to deliver an order from an empty queue");
            return null;
        }
        Order order = orders.removeFirst();
        logger.info("Order delivered: #{} for {}", order.orderNumber(), order.customerName());
        return order;
    }

    public Order deliver(int orderNumber) {
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.orderNumber() == orderNumber) {
                iterator.remove();
                logger.info("Order delivered by number: #{} for {}", orderNumber, order.customerName());
                return order;
            }
        }
        logger.error("Order with number {} not found", orderNumber);
        return null;
    }

    public void draw() {
        System.out.println("Num | Name");
        for (Order order : orders) {
            System.out.printf("%d | %s%n", order.orderNumber(), order.customerName());
        }
        logger.info("Current queue displayed in console");
    }
}

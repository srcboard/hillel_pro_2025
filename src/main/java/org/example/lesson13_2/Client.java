package org.example.lesson13_2;

class Client implements Runnable {
    private final Bar bar;
    private final Order order;
    private final int clientNumber;

    public Client(Bar bar, Order order, int clientNumber) {
        this.bar = bar;
        this.order = order;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Client " + clientNumber);
        bar.placeOrder(order);
    }
}

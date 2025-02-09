package org.example.lesson13_2;

class Bartender implements Runnable {
    private final Bar bar;

    public Bartender(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void run() {
        while (bar.isOpen()) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bar.receiveAndProcessOrder();
        }
    }
}

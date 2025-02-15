package org.example.lesson13_2;

class Bartender implements Runnable {
    private final Bar bar;

    public Bartender(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void run() {
        boolean isBarOpen = true;
        while (isBarOpen) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            isBarOpen = bar.receiveAndProcessOrder();
        }
    }
}

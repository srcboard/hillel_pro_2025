package org.example.lesson13_2;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int bartendersCount = 2;
        int clientsCount = 5;

        Bar bar = new Bar(clientsCount);

        for (int i = 0; i < bartendersCount; i++) {
            new Thread(new Bartender(bar), "Bartender " + (i + 1)).start();
        }

        try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5)) {
            Random random = new Random();
            for (int i = 0; i < clientsCount; i++) {
                Drink drink = Drink.values()[random.nextInt(Drink.values().length)];
                Client client = new Client(bar, new Order(drink), i + 1);
                scheduler.schedule(client, i + 1, TimeUnit.SECONDS);
            }
        }
    }
}

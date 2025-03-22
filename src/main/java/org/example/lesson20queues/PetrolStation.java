package org.example.lesson20queues;

import java.util.Optional;
import java.util.concurrent.*;

class PetrolStation {
    private int amount;
    private final BlockingQueue<Vehicle> queue = new LinkedBlockingQueue<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    private int countVehicles;

    public PetrolStation(int initialAmount, int countVehicles) {
        this.amount = initialAmount;
        this.countVehicles = countVehicles;
    }

    public void startProcessingQueue() {

        new Thread(() -> {
            while (!executor.isShutdown()) {
                try {
                    Optional.ofNullable(queue.poll(1, TimeUnit.SECONDS))
                            .ifPresent(v -> executor.submit(() -> doRefuel(v)));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

    }

    public void requestRefuel(Vehicle vehicle) {

        try {
            queue.put(vehicle);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private void doRefuel(Vehicle vehicle) {

        try {
            int waitTime = ThreadLocalRandom.current().nextInt(3, 11);
            Thread.sleep(waitTime * 1000L);

            synchronized (this) {
                if (amount >= vehicle.fuelAmount()) {
                    amount -= vehicle.fuelAmount();
                    countVehicles -= 1;
                    System.out.printf("%s has been refueled with %d liters. Remaining fuel: %d liters%n",
                            vehicle.name(),
                            vehicle.fuelAmount(),
                            amount);
                } else {
                    System.out.printf("Not enough fuel for %s (%s liters)%n",
                            vehicle.name(),
                            vehicle.fuelAmount());
                }
                if (countVehicles == 0) {
                    executor.shutdown();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}

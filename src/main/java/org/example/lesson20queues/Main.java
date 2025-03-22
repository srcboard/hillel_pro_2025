package org.example.lesson20queues;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int countVehicles = 5;
        PetrolStation station = new PetrolStation(100, countVehicles);

        station.startProcessingQueue();

        IntStream.range(1, countVehicles + 1)
                .mapToObj(i -> new Vehicle(
                        "Auto " + i, ThreadLocalRandom.current().nextInt(5, 21)))
                .forEach(station::requestRefuel);
    }

}

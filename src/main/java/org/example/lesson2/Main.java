package org.example.lesson2;

public class Main {

    private static final double CONV_K = 0.621371;

    public static void main(String[] args) {
        System.out.println("App for distance converting.");
        System.out.println("Version 1.0.");
        double kilometers = 10;
        double miles = convKmToMiles(kilometers);
        System.out.println(kilometers + " kilometers is equal to " + miles + " miles.");
    }

    private static double convKmToMiles(double kilometers) {
        return kilometers * CONV_K;
    }
}

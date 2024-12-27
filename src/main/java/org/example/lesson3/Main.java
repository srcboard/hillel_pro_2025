package org.example.lesson3;

public class Main {

    public static void main(String[] args) {
        System.out.println("App for temperature conversion.");
        System.out.println("Version 1.0.");

        double fahrenheit = 98.6;
        double celsius = 37.0;

        double celsiusResult = convFahrenheitToCelsius(fahrenheit);
        double fahrenheitResult = convCelsiusToFahrenheit(celsius);

        System.out.println("Result: " + fahrenheit + " °F = " + celsiusResult + " °C");
        System.out.println("Result: " + celsius + " °C = " + fahrenheitResult + " °F");
    }

    private static double convFahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private static double convCelsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }
}

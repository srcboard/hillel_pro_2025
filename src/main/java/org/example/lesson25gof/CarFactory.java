package org.example.lesson25gof;

class CarFactory extends TransportFactory {
    @Override
    Transport createTransport() {
        return new Car();
    }
}

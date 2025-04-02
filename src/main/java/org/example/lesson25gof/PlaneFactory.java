package org.example.lesson25gof;

class PlaneFactory extends TransportFactory {
    @Override
    Transport createTransport() {
        return new Plane();
    }
}

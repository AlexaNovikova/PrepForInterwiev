package ru.geekbrains.codeReview;

class LightWeightCar extends Car implements Moveable, Stopable{

    public LightWeightCar(String engineTitle, String color, String name) {
        super(engineTitle, color, name);
    }

    @Override
    void start() {
        System.out.println("LightWeightCar is start");
    }

    @Override
    void open() {
        System.out.println("LightWeightCar is open");
    }

    @Override
    public void move() {
        System.out.println("LightWeightCar is moving");
    }

    @Override
    public void stop() {
        System.out.println("LightWeightCar is stop");
    }
}

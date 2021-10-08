package ru.geekbrains.codeReview;

public class Lorry extends Car implements Moveable, Stopable {

    public Lorry(String engineTitle, String color, String name) {
        super(engineTitle, color, name);
    }

    @Override
    void start() {
        System.out.println("Lorry is start");
    }

    @Override
    void open() {
        System.out.println("Lorry is open");
    }

    @Override
    public void move() {
        System.out.println("Lorry is moving");
    }

    @Override
    public void stop() {
        System.out.println("Lorry is stop");
    }
}

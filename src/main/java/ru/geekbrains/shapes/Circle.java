package ru.geekbrains.shapes;

public class Circle extends Shape implements Printable{

    private final double radius;

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI*(radius*radius);
    }

    @Override
    public void print() {
        System.out.println("Print Circle");
    }
}

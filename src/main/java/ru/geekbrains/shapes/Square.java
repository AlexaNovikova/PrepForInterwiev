package ru.geekbrains.shapes;

public class Square extends Shape implements Printable, Polygonal{

    private final double side;

    public Square(double side) {
        super("Square");
        this.side = side;
    }

    @Override
    public double area() {
        return side*side;
    }

    @Override
    public void print() {
        System.out.println("Print Square");
    }

    @Override
    public double perimeter() {
        return side*4;
    }
}

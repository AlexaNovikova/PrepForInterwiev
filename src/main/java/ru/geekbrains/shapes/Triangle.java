package ru.geekbrains.shapes;

public class Triangle extends Shape implements Printable, Polygonal{

    private double firstSide;
    private double secondSide;
    private double thirdSide;

    public Triangle(double firstSide, double secondSide, double thirdSide) {
        super("Triangle");
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    @Override
    public double area() {
        double p = 0.5 * perimeter();
        return Math.sqrt(p*(p-firstSide)*(p-secondSide)*(p-thirdSide));
    }

    @Override
    public void print() {
        System.out.println("Print Triangle");
    }

    @Override
    public double perimeter() {
        return firstSide+secondSide+thirdSide;
    }
}

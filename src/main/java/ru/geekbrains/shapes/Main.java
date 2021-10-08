package ru.geekbrains.shapes;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(2);
        Triangle triangle = new Triangle(5,3,3);
        Square square = new Square(4);
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(circle);
        shapes.add(triangle);
        shapes.add(square);
        for (Shape shape:shapes){
            System.out.println(shape.getName() + " area: " + shape.area());
            if(shape instanceof Printable){
                ((Printable) shape).print();
            }
            if (shape instanceof Polygonal){
                System.out.println(shape.getName()+ " perimeter: " +((Polygonal) shape).perimeter());
            }
        }
    }
}

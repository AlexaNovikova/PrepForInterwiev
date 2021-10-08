package ru.geekbrains.codeReview;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        Lorry lorry = new Lorry("LorryEngine", "red", "Lorry0987");
        LightWeightCar lightWeightCar = new LightWeightCar("LightWeightEngine", "black", "LWE0909");
        cars.add(lightWeightCar);
        cars.add(lorry);
        for (Car car: cars){
            car.start();
            car.open();
        }
        System.out.println();
        lorry.start();
        lorry.move();
        lorry.stop();
        lorry.open();
        System.out.println();
        lightWeightCar.start();
        lightWeightCar.move();
        lightWeightCar.stop();
        lightWeightCar.open();
    }
}

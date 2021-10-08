package ru.geekbrains.codeReview;

// Исправлено :
// 1. Добавлен класс Engine
// 2. Интерфейсы movable и stopable на мой взгляд не нужны, достаточно добавить астрактные методы move(),
// и stop() в астрактный класс Car, так как все наследники будут реализовывать эти методы.
// 3. Можно оставить интерфейсы, но тогда необходимо переопределить методы в классах, реализующих эти интерфейсы.
// 4. Extends - для наследования классов, implements - для реализации интерфейсов
// 5. добавлен конструктор, setter -ы убрала
// 6. Изменен модификатор доступа у Engine на private

abstract class Car {

    private final Engine engine;
    private final String color;
    private final String modelName;

    public Car(String engineTitle, String color, String modelName) {
        this.engine = new Engine(engineTitle);
        this.color = color;
        this.modelName = modelName;
    }

    private class Engine{
        private String title;

        public Engine(String title) {
            this.title = title;
        }
    }

    abstract void start();

    abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public String getColor() {
        return color;
    }

    public String getModelName() {
        return modelName;
    }

}


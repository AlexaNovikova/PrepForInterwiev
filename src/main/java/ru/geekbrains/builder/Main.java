package ru.geekbrains.builder;

public class Main {
    public static void main(String[] args) {

        Person person1 = new Person.Builder("Peter", "Peterson")
                .address("Douson str, 2")
                .age(22).gender("male")
                .country("USA")
                .build();

        Person person2 = new Person.Builder("Anna", "Filonenko")
                .age(34)
                .country("Russia")
                .gender("female")
                .phone("8-999-999-99-99")
                .build();

        System.out.println(person1.toString());
        System.out.println(person2.toString());
    }
}

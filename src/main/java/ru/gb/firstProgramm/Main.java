package ru.gb.firstProgramm;

//1. Реализовать программу, в которой два потока поочередно пишут ping и pong.

public class Main {

    static final Object mon = new Object();
    static volatile int num = 1;

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                for (int i = 0; i < 30; i++) {
                    synchronized (mon) {
                        while (num != 1) {
                            mon.wait();
                        }
                        System.out.println("ping");
                        num = 2;
                        mon.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 30; i++) {
                    synchronized (mon) {
                        while (num != 2) {
                            mon.wait();
                        }
                        System.out.println("pong");
                        num = 1;
                        mon.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

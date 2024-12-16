package ru.gb.counter;

public class PingPong {
    private final Object monitor;
    private final String voice;

    public PingPong(Object monitor, String voice) {
        this.monitor = monitor;
        this.voice = voice;
    }

    public void game() {
        synchronized (monitor) {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(voice);
                    Thread.sleep(500);
                    monitor.notify();
                    monitor.wait();
                } catch (InterruptedException ignored) {

                }
            }
            monitor.notify();
        }
    }

    public static void main(String[] args) {
        Object monitor = new Object();
        PingPong ping = new PingPong(monitor, "ping");
        PingPong pong = new PingPong(monitor, "pong");
        new Thread(ping::game).start();
        new Thread(pong::game).start();
    }
}

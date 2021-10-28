package ru.gb.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private volatile int counter;
    private final Lock lock = new ReentrantLock();

    public int value() {
        return counter;
    }

    public Counter() {
        counter = 0;
    }

    public void inc() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public void dec() {
        lock.lock();
        try {
            counter--;
        } finally {
            lock.unlock();
        }
    }
}

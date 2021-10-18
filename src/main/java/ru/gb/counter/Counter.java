package ru.gb.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private int c;
    private final Lock lock = new ReentrantLock();

    public int value() {
        return c;
    }

    public Counter() {
        c = 0;
    }

    public void inc()  {
        lock.lock();
        c++;
        lock.unlock();
    }

    public void dec()  {
        lock.lock();
        c--;
        lock.unlock();
    }
}

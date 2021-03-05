package ru.netology;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Manufacturer {
    private static final long ACCEPTANCE_OF_CAR = 2;
    public static final int SOLD_CAR = 1;
    private final Showroom showroom;
    Lock lock;
    Condition condition;

    public Manufacturer(Showroom showroom) {
        this.showroom = showroom;
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public Car sellCar() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " : Buy the car.");
            while (showroom.getCars().size() == 0) {
                System.out.println(Thread.currentThread().getName() + ": No Car in the showroom.");
                condition.await();
            }
            TimeUnit.SECONDS.sleep(SOLD_CAR);
            System.out.println(Thread.currentThread().getName() + ": Car bought");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Unlock - " + Thread.currentThread().getName());
            lock.unlock();
        }
        return showroom.getCars().remove(0);
    }

    public void createCar() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + ": Creates a car");
        try {
            TimeUnit.SECONDS.sleep(ACCEPTANCE_OF_CAR);
            showroom.getCars().add(new Car());
            System.out.println(Thread.currentThread().getName() + ": Car created.");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Unlock - " + Thread.currentThread().getName());
            lock.unlock();
        }
    }
}

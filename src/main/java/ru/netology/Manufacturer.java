package ru.netology;

import java.util.concurrent.TimeUnit;

public class Manufacturer {
    private static final long ACCEPTANCE_OF_CAR = 2;
    public static final int SOLD_CAR = 1;
    private final Showroom showroom;

    public Manufacturer(Showroom showroom) {
        this.showroom = showroom;
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " : Buy the car.");
            while (showroom.getCars().size() == 0) {
                System.out.println(Thread.currentThread().getName() + ": No Car in the showroom.");
                wait();
            }
            TimeUnit.SECONDS.sleep(SOLD_CAR);
            System.out.println(Thread.currentThread().getName() + ": Car bought");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return showroom.getCars().remove(0); // FIXME:
    }

    public synchronized void createCar() {
        System.out.println(Thread.currentThread().getName() + ": Creates a car");
        try {
            TimeUnit.SECONDS.sleep(ACCEPTANCE_OF_CAR);
            showroom.getCars().add(new Car());
            System.out.println(Thread.currentThread().getName() + ": Car created.");
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

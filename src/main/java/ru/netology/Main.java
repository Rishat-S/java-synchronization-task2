package ru.netology;

import java.util.concurrent.TimeUnit;

public class Main {

    public static final int TIMEOUT = 1;
    public static final int N_COUNT = 3;

    public static void main(String[] args) throws InterruptedException {
        Showroom showroom = new Showroom();
        int count = 0;

        do {
            count++;
            new Thread(showroom::sellCar, "Customer" + count).start();
            TimeUnit.SECONDS.sleep(TIMEOUT);
        } while (count != N_COUNT);

        count = 0;

        do {
            count++;
            new Thread(showroom::createCar, "Manufacturer" + count).start();
            TimeUnit.SECONDS.sleep(TIMEOUT);
        } while (count != N_COUNT);
    }
}

package ru.netology;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Showroom showroom = new Showroom();
        int count = 0;

        do {
            count++;
            new Thread(showroom::sellCar, "Customer" + count).start();
            TimeUnit.SECONDS.sleep(1);
        } while (count != 3);

        count = 0;

        do {
            count++;
            new Thread(showroom::createCar, "Manufacturer" + count).start();
            TimeUnit.SECONDS.sleep(1);
        } while (count != 3);
    }
}

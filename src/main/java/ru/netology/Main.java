package ru.netology;

public class Main {

    public static void main(String[] args) {
        Showroom showroom = new Showroom();
        int count = 0;

        while (true) {
            count++;
            new Thread(showroom::sellCar, "Customer" + count).start();
            new Thread(showroom::createCar, "Manufacturer" + count).start();
            if (count == 4) {
                break;
            }
        }
    }
}

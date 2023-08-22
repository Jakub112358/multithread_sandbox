package org.example;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ServiceClass service1 = new ServiceClass("A");
        service1.doTask();
        Thread.sleep(500);
        for (int i = 0; i < 101; i++) {
            ServiceClass service2 = new ServiceClass(String.valueOf(i));
            service2.doTask();
        }

        Thread.sleep(1500);
        ServiceClass service3 = new ServiceClass("C");
        service3.doTask();
    }
}
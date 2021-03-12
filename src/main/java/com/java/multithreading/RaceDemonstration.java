package com.java.multithreading;

import java.util.Random;

public class RaceDemonstration {
    public static void main(String[] args) {
        RaceCondition race = new RaceCondition();
        try {
            race.runTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class RaceCondition{
    int randInt;
    Random random = new Random(System.currentTimeMillis());
    void printer(){
        int i = 1000000;

        while (i != 0) {
            if (randInt % 5 == 0) {
                if (randInt % 5 != 0) {
                    System.out.println(randInt);
                }
            }
            i--;
        }

    }

    void modifier() {
        int i = 1000000;
        while (i != 0) {
            randInt = random.nextInt(1000);
            i--;
        }
    }

    public void runTest() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                printer();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                modifier();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
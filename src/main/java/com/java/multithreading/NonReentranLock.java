package com.java.multithreading;

public class NonReentranLock {

    public static void main(String[] args) {

        LockClass lc = new LockClass();

        try {
            //lc.lock();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lc.lock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("Acquired 1st lock");

            System.out.println("Trying to acquire second lock");
            //lc.lock();

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lc.lock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
            t1.join();

            t2.start();
            t2.join();
            System.out.println("Acquired 2nd lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }

}

class LockClass{
    boolean isLocked;

    public LockClass(){
        isLocked = false;
    }

    public synchronized void lock() throws InterruptedException {
        System.out.println("1st time");
//        while(isLocked) {
//            wait();
//        }
        isLocked = true;
    }

    public synchronized void unlock(){
        isLocked = false;
        notify();
    }

}

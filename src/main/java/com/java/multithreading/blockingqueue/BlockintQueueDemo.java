package com.java.multithreading.blockingqueue;


public class BlockintQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        final BlockingQueue<Integer> queue = new BlockingQueue(5);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 50; i++) {
                        queue.enque(new Integer(i));
                        System.out.println("enqueued " + i);
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        //t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 25; i++) {
                        System.out.println("Dequed 1st : " + queue.deque());
                    }
                }catch (InterruptedException e) {

                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 25; i++) {
                        System.out.println("Dequed 2nd : " + queue.deque());
                    }
                }catch (InterruptedException e) {

                }
            }
        });
        t1.start();
        Thread.sleep(4000);
        t2.start();

        t2.join();

        t3.start();
        t1.join();
        t3.join();
    }
}

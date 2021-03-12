package com.java.multithreading.blockingqueue;

public class BlockingQueue<T> {
    T[] array;
    int capacity;
    int size;
    int head = 0;
    int tail = 0;

    Object lock = new Object();

    public BlockingQueue(int capacity){
        this.capacity = capacity;
        size = 0;
        array = (T[]) new Object[capacity];
    }

    public void enque(T e) throws InterruptedException {
        synchronized (lock) {
            while (size == capacity) {
                lock.wait();
            }

            if (tail == capacity) {
                tail = 0;
            }
            array[tail] = e;
            tail++;
            size++;
            lock.notifyAll();
        }
    }

    public T deque()throws InterruptedException{
        T item = null;
        synchronized (lock) {
            while (size == 0) {
                lock.wait();
            }

            if (head == capacity) {
                head = 0;
            }

            item = array[head];
            array[head] = null;
            head++;
            size--;
            lock.notifyAll();

        }
        return item;
    }
}

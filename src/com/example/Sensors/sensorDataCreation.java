package com.example.Sensors;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Random;

/**
 * Created by Pritam on 04-Sep-16.
 */
public class sensorDataCreation {

    public static void main(String[] args) {
        int size = 4;

        int[] processedQueue = new int[10];
        for(int i = 0; i < 10; i++){
            processedQueue[i] = -1;
        }

        Vector sharedQueue1 = new Vector();
        Thread prodThread1 = new Thread(new Producer(sharedQueue1, processedQueue, size), "Producer1");
        prodThread1.start();

        Vector sharedQueue2 = new Vector();
        Thread prodThread2 = new Thread(new Producer(sharedQueue2, processedQueue, size), "Producer2");
        prodThread2.start();

        Vector sharedQueue3 = new Vector();
        Thread prodThread3 = new Thread(new Producer(sharedQueue3, processedQueue, size), "Producer3");
        prodThread3.start();

        Vector sharedQueue4 = new Vector();
        Thread prodThread4 = new Thread(new Producer(sharedQueue4, processedQueue, size), "Producer4");
        prodThread4.start();

        Vector sharedQueue5 = new Vector();
        Thread prodThread5 = new Thread(new Producer(sharedQueue5, processedQueue, size), "Producer5");
        prodThread5.start();

        Vector sharedQueue6 = new Vector();
        Thread prodThread6 = new Thread(new Producer(sharedQueue6, processedQueue, size), "Producer6");
        prodThread6.start();

        Vector sharedQueue7 = new Vector();
        Thread prodThread7 = new Thread(new Producer(sharedQueue7, processedQueue, size), "Producer7");
        prodThread7.start();

        Vector sharedQueue8 = new Vector();
        Thread prodThread8 = new Thread(new Producer(sharedQueue8, processedQueue, size), "Producer8");
        prodThread8.start();

        Vector sharedQueue9 = new Vector();
        Thread prodThread9 = new Thread(new Producer(sharedQueue9, processedQueue, size), "Producer9");
        prodThread9.start();

        Vector sharedQueue10 = new Vector();
        Thread prodThread10 = new Thread(new Producer(sharedQueue10, processedQueue, size), "Producer10");
        prodThread10.start();

        Thread prosThread = new Thread(new Processor(processedQueue, sharedQueue1, sharedQueue2, sharedQueue3,
                sharedQueue4, sharedQueue5, sharedQueue6, sharedQueue7, sharedQueue8, sharedQueue9, sharedQueue10,
                size), "ProcessorThread");
        prosThread.start();

        Thread consThread = new Thread(new Consumer(processedQueue, size), "Consumer");
        consThread.start();
    }
}

class Processor implements Runnable {
    private final int[] processedQueue;
    private final Vector sharedQueue1, sharedQueue2, sharedQueue3, sharedQueue4,
            sharedQueue5, sharedQueue6, sharedQueue7, sharedQueue8, sharedQueue9, sharedQueue10;
    private final int SIZE;

    public Processor(int[] processedQueue, Vector sharedQueue1, Vector sharedQueue2, Vector sharedQueue3, Vector sharedQueue4,
                     Vector sharedQueue5, Vector sharedQueue6, Vector sharedQueue7, Vector sharedQueue8, Vector sharedQueue9,
                     Vector sharedQueue10, int size) {
        this.processedQueue = processedQueue;
        this.sharedQueue1 = sharedQueue1;
        this.sharedQueue2 = sharedQueue2;
        this.sharedQueue3 = sharedQueue3;
        this.sharedQueue4 = sharedQueue4;
        this.sharedQueue5 = sharedQueue5;
        this.sharedQueue6 = sharedQueue6;
        this.sharedQueue7 = sharedQueue7;
        this.sharedQueue8 = sharedQueue8;
        this.sharedQueue9 = sharedQueue9;
        this.sharedQueue10 = sharedQueue10;
        this.SIZE = size;
    }

    @Override
    public void run() {
        while(true) {
            for (int i = 0; i < 10; i++) {
                try {
                    process(i);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void process(int i) throws InterruptedException {
        if(i == 0) {
            //wait if queue is empty
            while (sharedQueue1.isEmpty()) {
                synchronized (processedQueue) {
                    System.out.println("Queue is empty. " + Thread.currentThread().getName()
                            + " is waiting , sharedQueue1 size: " + sharedQueue1.size());

                    processedQueue.wait();
                }
            }

            synchronized (sharedQueue1) {
                if (processedQueue[i] == -1) {
                    processedQueue[i] = binToInt((String) sharedQueue1.remove(0));
                    System.out.println("processedQueue[" + i + "] = " + processedQueue[i]);
                    sharedQueue1.notifyAll();
                }
            }
        } else if(i==1){
            //wait if queue is empty
            while (sharedQueue2.isEmpty()) {
                synchronized (processedQueue) {
                    System.out.println("Queue is empty. " + Thread.currentThread().getName()
                            + " is waiting , sharedQueue1 size: " + sharedQueue2.size());

                    processedQueue.wait();
                }
            }

            synchronized (sharedQueue2) {
                if (processedQueue[i] == -1) {
                    processedQueue[i] = binToInt((String) sharedQueue2.remove(0));
                    System.out.println("processedQueue[" + i + "] = " + processedQueue[i]);
                    sharedQueue2.notifyAll();
                }
            }
        } else if(i==2){
            //wait if queue is empty
            while (sharedQueue3.isEmpty()) {
                synchronized (processedQueue) {
                    System.out.println("Queue is empty. " + Thread.currentThread().getName()
                            + " is waiting , sharedQueue1 size: " + sharedQueue3.size());

                    processedQueue.wait();
                }
            }

            synchronized (sharedQueue3) {
                if (processedQueue[i] == -1) {
                    processedQueue[i] = binToInt((String) sharedQueue3.remove(0));
                    System.out.println("processedQueue[" + i + "] = " + processedQueue[i]);
                    sharedQueue3.notifyAll();
                }
            }
        } else if(i==3){
            //wait if queue is empty
            while (sharedQueue4.isEmpty()) {
                synchronized (processedQueue) {
                    System.out.println("Queue is empty. " + Thread.currentThread().getName()
                            + " is waiting , sharedQueue1 size: " + sharedQueue4.size());

                    processedQueue.wait();
                }
            }

            synchronized (sharedQueue4) {
                if (processedQueue[i] == -1) {
                    processedQueue[i] = binToInt((String) sharedQueue4.remove(0));
                    System.out.println("processedQueue[" + i + "] = " + processedQueue[i]);
                    sharedQueue4.notifyAll();
                }
            }
        } else if(i==4){
            //wait if queue is empty
            while (sharedQueue5.isEmpty()) {
                synchronized (processedQueue) {
                    System.out.println("Queue is empty. " + Thread.currentThread().getName()
                            + " is waiting , sharedQueue1 size: " + sharedQueue5.size());

                    processedQueue.wait();
                }
            }

            synchronized (sharedQueue5) {
                if (processedQueue[i] == -1) {
                    processedQueue[i] = binToInt((String) sharedQueue5.remove(0));
                    System.out.println("processedQueue[" + i + "] = " + processedQueue[i]);
                    sharedQueue5.notifyAll();
                }
            }
        } else if(i==5){
            //wait if queue is empty
            while (sharedQueue6.isEmpty()) {
                synchronized (processedQueue) {
                    System.out.println("Queue is empty. " + Thread.currentThread().getName()
                            + " is waiting , sharedQueue1 size: " + sharedQueue6.size());

                    processedQueue.wait();
                }
            }

            synchronized (sharedQueue6) {
                if (processedQueue[i] == -1) {
                    processedQueue[i] = binToInt((String) sharedQueue6.remove(0));
                    System.out.println("processedQueue[" + i + "] = " + processedQueue[i]);
                    sharedQueue6.notifyAll();
                }
            }
        } else if(i==6){
            //wait if queue is empty
            while (sharedQueue7.isEmpty()) {
                synchronized (processedQueue) {
                    System.out.println("Queue is empty. " + Thread.currentThread().getName()
                            + " is waiting , sharedQueue1 size: " + sharedQueue7.size());

                    processedQueue.wait();
                }
            }

            synchronized (sharedQueue7) {
                if (processedQueue[i] == -1) {
                    processedQueue[i] = binToInt((String) sharedQueue7.remove(0));
                    System.out.println("processedQueue[" + i + "] = " + processedQueue[i]);
                    sharedQueue7.notifyAll();
                }
            }
        } else if(i==7){
            //wait if queue is empty
            while (sharedQueue8.isEmpty()) {
                synchronized (processedQueue) {
                    System.out.println("Queue is empty. " + Thread.currentThread().getName()
                            + " is waiting , sharedQueue1 size: " + sharedQueue8.size());

                    processedQueue.wait();
                }
            }

            synchronized (sharedQueue8) {
                if (processedQueue[i] == -1) {
                    processedQueue[i] = binToInt((String) sharedQueue8.remove(0));
                    System.out.println("processedQueue[" + i + "] = " + processedQueue[i]);
                    sharedQueue8.notifyAll();
                }
            }
        } else if(i==8){
            //wait if queue is empty
            while (sharedQueue9.isEmpty()) {
                synchronized (processedQueue) {
                    System.out.println("Queue is empty. " + Thread.currentThread().getName()
                            + " is waiting , sharedQueue1 size: " + sharedQueue9.size());

                    processedQueue.wait();
                }
            }

            synchronized (sharedQueue9) {
                if (processedQueue[i] == -1) {
                    processedQueue[i] = binToInt((String) sharedQueue9.remove(0));
                    System.out.println("processedQueue[" + i + "] = " + processedQueue[i]);
                    sharedQueue9.notifyAll();
                }
            }
        } else if(i==9){
            //wait if queue is empty
            while (sharedQueue10.isEmpty()) {
                synchronized (processedQueue) {
                    System.out.println("Queue is empty. " + Thread.currentThread().getName()
                            + " is waiting , sharedQueue1 size: " + sharedQueue10.size());

                    processedQueue.wait();
                }
            }

            synchronized (sharedQueue10) {
                if (processedQueue[i] == -1) {
                    processedQueue[i] = binToInt((String) sharedQueue10.remove(0));
                    System.out.println("processedQueue[" + i + "] = " + processedQueue[i]);
                    sharedQueue10.notifyAll();
                }
            }
        }
    }

    private int binToInt(String binaryStr) throws InterruptedException {
        int returnValue = 0;
        try {
            returnValue = Integer.parseInt(binaryStr, 2);
        } catch (NumberFormatException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnValue;
    }
}

// Producer Thread
class Producer implements Runnable {

    private final int[] processedQueue;
    private final Vector sharedQueue;
    private final int SIZE;

    public Producer(Vector sharedQueue, int[] processedQueue, int size) {
        this.processedQueue = processedQueue;
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
    }

    @Override
    public void run() {
        int a = 0;
        while(a < 7){
            //System.out.println("Produced: " + i);
            try {
                produce();
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            a++;
        }
    }

    private void produce() throws InterruptedException {

        //wait if queue is full
        while (sharedQueue.size() == SIZE) {
            synchronized (sharedQueue) {
                System.out.println("Queue is full. " + Thread.currentThread().getName()
                        + " is waiting , size: " + sharedQueue.size());

                sharedQueue.wait();
            }
        }

        //producing element and notify consumers
        synchronized (processedQueue) {
            String temp = binNumber();
            sharedQueue.add(temp);
            //System.out.println(Thread.currentThread().getName() + ": added  to sharedQueue : " + temp);
            processedQueue.notifyAll();
        }
    }

    @NotNull
    public static String binNumber() {
        Random rg = new Random();
        int n = rg.nextInt(256);
        return String.format("%8s",Integer.toBinaryString(n)).replace(' ', '0');
    }
}

// Consumes the output of sensors form the processedQueue
class Consumer implements Runnable {

    private final int[] processedQueue;
    private final int SIZE;

    public Consumer(int[] processedQueue, int size) {
        this.processedQueue = processedQueue;
        this.SIZE = size;
    }

    @Override
    public void run() {
        while (true) {
            try {
                calculate();
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void calculate() throws InterruptedException {
        int[] temp_array = new int[10];
        int negative_count = 10;
        //wait if array contains -1
        while (negative_count > 0) {
            synchronized (processedQueue) {
                int temp = 0;
                for(int i=0;i<10;i++){
                    if(processedQueue[i] < 0){
                        temp++;
                    } else {
                        temp_array[i] = processedQueue[i];
                    }
                }
                negative_count = temp;
                processedQueue.wait();
            }
        }

        //Otherwise consume element and notify waiting producer
        synchronized (processedQueue) {
            int sum = 0;
            long mult = 1;
            for(int i=0;i<10;i++){
                sum += processedQueue[i];
                mult *= (long)processedQueue[i];
                processedQueue[i] = -1;
            }

            if(sum > 10000){
                System.out.println("State detected from sum.");
            } else {
                System.out.println("State not detected from sum.");
            }

            if((sum/10) > 100){
                System.out.println("State detected from avg.");
            } else {
                System.out.println("State not detected from avg.");
            }

            if(mult > 100000){
                System.out.println("State detected from mult.");
            } else {
                System.out.println("State not detected from mult.");
            }
            processedQueue.notifyAll();
        }
    }
}

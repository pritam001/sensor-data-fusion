package com.example.SyncedSensors;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ArrayBlockingQueue;

import java.util.Random;

/**
 * Created by Pritam on 04-Sep-16.
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

class SensorDataProcessing
{
    public static BlockingQueue<String>[] queue = new LinkedBlockingQueue[10];  //random binary strings of sensors
    public static BlockingQueue<Integer> randInts = new LinkedBlockingQueue();  //binary strings converted to integers
    public static Integer avg, mul, sum;                                        //average, multiply and addition
    public static final int MYTHREADS = 12; //total threads used

    public static void main(String[] args)
    {

        ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);     //create a thread pool

        for(int i=0; i<10; i++) {                           //10 threads for storing output of 10 sensors
            queue[i] = new LinkedBlockingQueue();
            Runnable worker  = new GenRand("thread"+i, i);
            executor.execute(worker);
        }

        Runnable worker1 = new RandInteger("threadConv");  //thread to convert Strings to integer
        executor.execute(worker1);

        Runnable worker2 = new DataFusion("threadDataFus"); //thread for data fusion and threshold check
        executor.execute(worker2);

        executor.shutdown();    //shut down the executor after all the threads are completed
    }
}

//Handle the two threads for computing operations
class DataFusion implements Runnable{

    public Thread t;
    private final String threadName;

    DataFusion(String name) {
        threadName = name;
    }

    @Override
    public void run() {
        int t=1;
        SensorDataProcessing.sum=0;
        SensorDataProcessing.mul=1;
        while(t!=51) {

            try {
                ExecutorService exec = Executors.newFixedThreadPool(2);  //start two threads for addition and multiplication

                int num = SensorDataProcessing.randInts.take();

                Runnable worker1 = new Operations("threadAdd", 0, num, t);
                exec.execute(worker1);
                Runnable worker2 = new Operations("threadMul", 1, num, t);
                exec.execute(worker2);

                exec.shutdown();        //shut down the exec when the two threads end

                while(!exec.isTerminated()) {}

                t++;
            } catch (InterruptedException ex) {
                Logger.getLogger(DataFusion.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR");
            }

        }
        System.out.println("Thread: " + threadName + "exiting");
    }
}


//apply different operations and check result with threshold
class Operations implements Runnable {
    public Thread t;
    private final String threadName;
    private final int op;
    private final int num;
    private final int count;

    Operations(String name, int ops, int rand, int c) {
        threadName = name;
        op=ops;
        num=rand;
        count = c;
    }

    @Override
    public void run() {
        switch(op) {
            case 0: addition();
                break;
            case 1: multiplication();
                break;
            default: break;
        }
    }

    private void addition() {           //add and compute average till the current integer
        SensorDataProcessing.sum+=num;
        SensorDataProcessing.avg=SensorDataProcessing.sum/count;
        result(0);
    }

    private void multiplication() {     //multiply the
        SensorDataProcessing.mul*=num;
        result(1);
    }

    private void result(int res) {         //compare the result with the threshold
        int r=res;
        if(r==0) {
            if(SensorDataProcessing.sum>10000) {
                System.out.println("State not detected from add");
            }
            else {
                System.out.println("State detected from add");
            }

            if(SensorDataProcessing.avg>100) {
                System.out.println("State not detected from avg");
            }
            else {
                System.out.println("State detected from avg");
            }
        }
        else {
            if(SensorDataProcessing.mul>100000) {
                System.out.println("State not detected from mul");
            }
            else {
                System.out.println("State detected from mul");
            }
        }
    }

}

class GenRand implements Runnable{

    public Thread t;
    private final String threadName;
    private String randBinary;
    private final int sensor;

    GenRand(String name, int num) {
        threadName = name;
        sensor=num;
    }

    @Override
    public void run() {
        for(int j=100; j>=0; j--) {

            Random randomGenerator = new Random();
            randBinary="";
            for(int i=7; i>=0; i--) {       //geenrated random binary string
                int rand = randomGenerator.nextInt(2);
                randBinary+=Integer.toString(rand);
            }

            System.out.println(sensor+" "+randBinary);

            //store the generated binary string
            try {
                SensorDataProcessing.queue[sensor].put(randBinary);
            } catch (InterruptedException ex) {
                Logger.getLogger(GenRand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class RandInteger implements Runnable{

    public Thread t;
    private final String threadName;

    RandInteger(String name) {
        threadName = name;
    }

    @Override
    public void run() {
        int t=10;
        while(t!=0) {
            //convert binary string of each sensor into integer and store into another queue
            for(int i=0; i<10; i++) {
                try {
                    SensorDataProcessing.randInts.put(Integer.parseInt(SensorDataProcessing.queue[i].take(), 2));
                } catch (InterruptedException ex) {
                    Logger.getLogger(RandInteger.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            t--;
        }
    }
}

package com.example.Sensors;
import java.util.Random;

/**
 * Created by Pritam on 04-Sep-16.
 */
public class sensorDataCreation {
    public static void main(String[] args) {
        String[] dataArray = new String[10];
        Runnable task = () -> {
                String threadName = Thread.currentThread().getName();
                for(int i = 0; i < 4; i++) {
                    int num = Integer.parseInt(threadName.split("-")[1]);
                    String curr_value = binNumber();
                    System.out.println("Detected " + curr_value + " on " + threadName);
                    dataArray[num] = curr_value;
                }
                //System.out.println("Waited 1 second " + threadName);
        };

        //task.run();

        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }

        System.out.println("Done!");
    }

    public static String binNumber() {
        Random rg = new Random();
        int n = rg.nextInt(256);
        return String.format("%8s",Integer.toBinaryString(n)).replace(' ', '0');
    }
}

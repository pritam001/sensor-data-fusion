package com.example.helloworld;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pritam on 02-Sep-16.
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Runnable task = () -> {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println("Hello " + threadName);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Waited 1 second " + threadName);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        };

        task.run();

        for(int i = 0; i < 4; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }

        System.out.println("Done!");
    }
}

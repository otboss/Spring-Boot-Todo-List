package com.otboss.todo.service;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class DistributionCenterService {


    public void getClosestDistributionCenter() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println(executor);

        // Submit tasks
        for (int i = 1; i <= 5; i++) {
            final int taskNumber = i;
            executor.submit(() -> {
                System.out.println("Executing Task " + taskNumber + " by " + Thread.currentThread().getName());
            });
        }

        // Shut down the executor
        executor.shutdown();

        try {
            // Wait for all tasks to finish
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Forcing shutdown...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("done execution");
    }

}
package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        Random rand = new Random();

        for (int i = 1; i <= 20; i++) {
            Task t = new Task(
                    String.valueOf(i),
                    "Task " + i,
                    rand.nextInt(10) + 1,
                    LocalDateTime.now().plusMinutes(rand.nextInt(500)),
                    (long) (rand.nextInt(60) + 1),
                    Status.PENDING
            );

            scheduler.submit(t);
        }

        List<Task> processed = scheduler.processBatch(20);

        long total = 0;

        for (Task t : processed) {
            System.out.println(t.getName() + " | priority=" + t.getPriority());
            total += t.getEstimatedMinutes();
        }

        System.out.println("Total time = " + total + " minutes");


    }
}
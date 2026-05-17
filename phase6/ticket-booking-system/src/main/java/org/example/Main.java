package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.core.TicketSystem;
import org.example.request.BookingRequest;
import org.example.request.BrowseRequest;
import org.example.request.UserRequest;

import java.util.Random;
import java.util.concurrent.*;
@Slf4j
public class Main {
    public static void main(String[] args) {
        int numOfUsers = 50;
        TicketSystem ticketSystem = new TicketSystem(20);
        int corePoolSize = 5;
        int maxPoolSize = 10;
        int queueCapacity = 20;
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, 60, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(queueCapacity),
                        new ThreadPoolExecutor.AbortPolicy()
                );
        Random random = new Random();

        for (int i = 1; i <= numOfUsers; i++) {
            String userName = "User-" + i;
            try {
                int action = random.nextInt(2);
                UserRequest request;
                if (action == 0) {
                    request = new BrowseRequest(ticketSystem, userName);
                } else {
                    request = new BookingRequest(ticketSystem, userName);
                }

                executor.execute(request);

                Thread.sleep(300);

            } catch (RejectedExecutionException e) {
                log.warn("Request rejected for {}", userName);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        executor.shutdown();
    }

    }

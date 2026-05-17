package org.example.core;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class TicketSystem {
    private int availableSeats;
    private final ReentrantLock bookingLock = new ReentrantLock();

    public TicketSystem(int availableSeats){
        this.availableSeats = availableSeats;
    }

    public void browse(String userName){
        log.info("{} -> {} is browsing. Available seats = {}",
                Thread.currentThread().getName(),
                userName,
                availableSeats);
    }

    public boolean bookSeat(String userName) {

        try {
            if (bookingLock.tryLock(2, TimeUnit.SECONDS)) {

                try {
                    log.info("{} is trying to book", userName);
                    Thread.sleep(1000);
                    if (availableSeats > 0) {
                        availableSeats--;
                        log.info(
                                "SUCCESS -> {} booked a seat. Remaining seats = {}",
                                userName,
                                availableSeats
                        );
                        return true;
                    }
                    log.warn("FAILED -> No seats left for {}", userName);
                    return false;
                } finally {
                    bookingLock.unlock();
                }

            } else {
                log.warn("{} could not acquire lock within timeout", userName);
                return false;
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread interrupted while booking for {}", userName);
            return false;
        }
    }
}

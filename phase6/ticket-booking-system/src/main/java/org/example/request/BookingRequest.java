package org.example.request;

import org.example.core.TicketSystem;

public class BookingRequest implements UserRequest {
    private final TicketSystem ticketSystem;
    private  String userName;

    public BookingRequest(TicketSystem ticketSystem, String userName) {
        this.ticketSystem = ticketSystem;
        this.userName = userName;
    }

    @Override
    public void run() {
        ticketSystem.bookSeat(userName);
    }
}

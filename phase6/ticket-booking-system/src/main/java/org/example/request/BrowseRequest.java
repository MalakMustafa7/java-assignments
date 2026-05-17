package org.example.request;

import org.example.core.TicketSystem;

public class BrowseRequest implements UserRequest {
    private final TicketSystem ticketSystem;
    private  String userName;

    public BrowseRequest(TicketSystem ticketSystem, String userName) {
        this.ticketSystem = ticketSystem;
        this.userName = userName;
    }
    @Override
    public void run() {
        ticketSystem.browse(userName);
    }
}

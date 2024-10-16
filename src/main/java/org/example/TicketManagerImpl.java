package org.example;

import java.util.Arrays;

public class TicketManagerImpl implements TicketManager {
    private Ticket[] tickets;

    public TicketManagerImpl() {
        this.tickets = new Ticket[0];
    }

    @Override
    public void add(Ticket ticket) {
        if (ticket == null)  {
            throw new NullPointerException("Ticket is null");
        }

        tickets = Arrays.copyOf(tickets, tickets.length + 1);
        int pos = tickets.length - 1;

        if (tickets.length == 1) {
            tickets[pos] = ticket;
            return;
        }

        while (pos > 0) {
            int parentPos = pos - 1;
            Ticket parent = tickets[parentPos];

            if (parent.compareTo(ticket) >= 0) {
                break;
            }

            tickets[pos] = parent;
            pos = parentPos;
        }
        tickets[pos] = ticket;
    }

    @Override
    public Ticket next() {
        if (tickets.length == 0) {
            return null;
        }

        Ticket next = tickets[tickets.length - 1];
        tickets = Arrays.copyOf(tickets, tickets.length - 1);

        return next;
    }
}

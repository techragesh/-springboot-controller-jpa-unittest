package com.techjava.ticketbooking.services;

import com.techjava.ticketbooking.model.Ticket;



public interface TicketBookingService {

    Ticket findTicketByEmail(String email);
    Iterable<Ticket> getAllTickets();
    Ticket findTicketById(Integer ticketId);
    Ticket updateEmailById(Integer ticketId, String email);
    boolean deleteTicketById(Integer ticketId);
    Ticket createTicket(Ticket ticket);

}

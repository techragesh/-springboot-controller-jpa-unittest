package com.techjava.ticketbooking.dao;

import com.techjava.ticketbooking.model.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketBookingJpaDao extends CrudRepository<Ticket,Integer>{

    Ticket findByEmail(String email);

}

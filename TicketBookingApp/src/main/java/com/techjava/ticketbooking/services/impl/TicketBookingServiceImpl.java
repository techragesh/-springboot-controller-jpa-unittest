package com.techjava.ticketbooking.services.impl;

import com.techjava.ticketbooking.dao.TicketBookingJpaDao;
import com.techjava.ticketbooking.model.Ticket;
import com.techjava.ticketbooking.services.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TicketBookingServiceImpl implements TicketBookingService{

    @Autowired
    private TicketBookingJpaDao ticketBookingJpaDao;


    @Override
    public Ticket findTicketByEmail(String email) {
        return ticketBookingJpaDao.findByEmail(email);
    }

    @Override
    public Iterable<Ticket> getAllTickets() {
        return ticketBookingJpaDao.findAll();
    }

    @Override
    public Ticket findTicketById(Integer ticketId) {
        return ticketBookingJpaDao.findOne(ticketId);
    }

    @Override
    public Ticket updateEmailById(Integer ticketId, String email) {
        Ticket ticket = ticketBookingJpaDao.findOne(ticketId);
        ticket.setEmail(email);
        return ticketBookingJpaDao.save(ticket);
    }

    @Override
    public boolean deleteTicketById(Integer ticketId) {
        ticketBookingJpaDao.delete(ticketId);
        Ticket ticket = ticketBookingJpaDao.findOne(ticketId);
        if(null == ticket){
            return true;
        }
        return false;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketBookingJpaDao.save(ticket);
    }
}

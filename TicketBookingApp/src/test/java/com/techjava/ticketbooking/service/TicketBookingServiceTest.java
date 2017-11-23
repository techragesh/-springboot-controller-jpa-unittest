package com.techjava.ticketbooking.service;


import com.techjava.ticketbooking.dao.TicketBookingJpaDao;
import com.techjava.ticketbooking.model.Ticket;
import com.techjava.ticketbooking.services.TicketBookingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketBookingServiceTest {

    @MockBean
    private TicketBookingJpaDao ticketBookingJpaDao;

    @Autowired
    private TicketBookingService ticketBookingService;

    @Test
    public void testCreateTicket(){
        Ticket ticket = new Ticket();
        ticket.setTicketId(100);
        ticket.setPassengerName("Kumar");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kumar@msn.com");

        Mockito.when(ticketBookingJpaDao.save(ticket)).thenReturn(ticket);
        assertThat(ticketBookingService.createTicket(ticket)).isEqualTo(ticket);
    }

    @Test
    public void testGetTicketById() throws Exception{
        Ticket ticket = new Ticket();
        ticket.setTicketId(102);
        ticket.setPassengerName("Kumar");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kumar@msn.com");

        Mockito.when(ticketBookingJpaDao.findOne(100)).thenReturn(ticket);
        assertThat(ticketBookingService.findTicketById(100)).isEqualTo(ticket);
    }

    @Test
    public void testGetAllBookedTickets() throws Exception{
        Ticket ticket1 = new Ticket();
        ticket1.setPassengerName("Suresh");
        ticket1.setFromStation("Chennai");
        ticket1.setToStation("Pune");
        ticket1.setBookingDate(new Date());
        ticket1.setEmail("ser@msn.com");

        Ticket ticket2 = new Ticket();
        ticket2.setPassengerName("Mani");
        ticket2.setFromStation("Chennai");
        ticket2.setToStation("Pune");
        ticket2.setBookingDate(new Date());
        ticket2.setEmail("mani@msn.com");

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        Mockito.when(ticketBookingJpaDao.findAll()).thenReturn(ticketList);
        assertThat(ticketBookingService.getAllTickets()).isEqualTo(ticketList);
    }


    @Test
    public void testFindByEmail() throws Exception{
        Ticket ticket = new Ticket();
        ticket.setPassengerName("Revi");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("revi@msn.com");

        Mockito.when(ticketBookingJpaDao.findByEmail("revi@msn.com")).thenReturn(ticket);
        assertThat(ticketBookingService.findTicketByEmail("revi@msn.com")).isEqualTo(ticket);
    }

    @Test
    public void testDeleteTicketById() throws Exception{
        Ticket ticket = new Ticket();
        ticket.setTicketId(105);
        ticket.setPassengerName("Kumar");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kumar@msn.com");

        Mockito.when(ticketBookingJpaDao.save(ticket)).thenReturn(ticket);
        Mockito.when(ticketBookingJpaDao.findOne(105)).thenReturn(ticket);
        ticketBookingJpaDao.delete(ticket.getTicketId());
        Mockito.when(ticketBookingJpaDao.findOne(105)).thenReturn(ticket);
        Assert.assertNotEquals(ticket, new Ticket());
        Assert.assertEquals(ticketBookingService.deleteTicketById(105), false);
    }

    @Test
    public void testDeleteTicketByNull() throws Exception{
        Ticket ticket = new Ticket();
        ticket.setTicketId(1005);
        Ticket nullTicket = null;
        Mockito.when(ticketBookingJpaDao.findOne(1005)).thenReturn(nullTicket);
        ticketBookingJpaDao.delete(ticket.getTicketId());
        Assert.assertEquals(ticketBookingService.deleteTicketById(1005), true);
    }

    @Test
    public void testUpdateTicket() throws Exception{

        Ticket ticket2 = new Ticket();
        ticket2.setTicketId(100);
        ticket2.setPassengerName("Maran");
        ticket2.setFromStation("Chennai");
        ticket2.setToStation("Pune");
        ticket2.setBookingDate(new Date());
        ticket2.setEmail("maran@msn.com");

        Mockito.when(ticketBookingJpaDao.findOne(100)).thenReturn(ticket2);
        ticket2.setEmail("maran100@msn.com");
        Mockito.when(ticketBookingJpaDao.save(ticket2)).thenReturn(ticket2);
        System.out.println(ticket2.getEmail());
        assertThat(ticketBookingService.updateEmailById(100, "maran100@msn.com")).isEqualTo(ticket2);
    }

}

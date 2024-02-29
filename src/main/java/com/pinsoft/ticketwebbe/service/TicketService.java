package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.entity.Ticket;
import com.pinsoft.ticketwebbe.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService extends AbstractBaseService<Ticket,Long> {

    @Autowired
    private TicketRepository ticketRepository;

    protected JpaRepository<Ticket, Long> getRepository() {
        return ticketRepository;
    }
}

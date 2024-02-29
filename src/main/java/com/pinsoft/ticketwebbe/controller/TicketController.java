package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.entity.Station;
import com.pinsoft.ticketwebbe.entity.Ticket;
import com.pinsoft.ticketwebbe.service.StationService;
import com.pinsoft.ticketwebbe.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/ticket")
    public Collection<Ticket> get(){
        return ticketService.listAll();
    }

    @GetMapping("/ticket/{id}")
    public Ticket get(@PathVariable Long id){
        return ticketService.get(id);
    }

    @DeleteMapping("/ticket/{id}")
    public void delete(@PathVariable Long id){
        ticketService.delete(id);
    }

    //TODO
    /*
    Change requestbody to TicketDto
     */
    @PostMapping("/ticket")
    public Ticket add(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }
}

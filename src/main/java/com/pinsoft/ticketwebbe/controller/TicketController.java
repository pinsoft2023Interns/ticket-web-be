package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.dto.TicketRequest;
import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.entity.Station;
import com.pinsoft.ticketwebbe.entity.Ticket;
import com.pinsoft.ticketwebbe.entity.User;
import com.pinsoft.ticketwebbe.service.BusService;
import com.pinsoft.ticketwebbe.service.StationService;
import com.pinsoft.ticketwebbe.service.TicketService;
import com.pinsoft.ticketwebbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;

    @Autowired
    BusService busService;

    @Autowired
    UserService userService;

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

    @PostMapping("/ticket")
    public Ticket add(@RequestBody TicketRequest ticketRequest) {
        Ticket ticket = new Ticket();
        ticket.setActive(true);
        ticket.setCanceled(false);
        ticket.setPrice(ticketRequest.getPrice());
        ticket.setSeatInfo(ticketRequest.getSeatInfo());
        Optional<User> user = userService.getById(ticketRequest.getUserId());
        ticket.setUser(user.get());
        Bus bus = busService.get(ticketRequest.getBusId());
        ticket.setBus(bus);
        return ticketService.save(ticket);
    }
}

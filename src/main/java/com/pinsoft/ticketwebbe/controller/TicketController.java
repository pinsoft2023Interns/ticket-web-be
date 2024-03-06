package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.dto.TicketRequest;
import com.pinsoft.ticketwebbe.dto.TicketUpdateRequest;
import com.pinsoft.ticketwebbe.entity.*;
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
        return ticketService.save(ticketRequest);
    }

    @PutMapping("/ticket/{id}")
    public Ticket add(@RequestBody TicketUpdateRequest ticketUpdateRequest){
        return ticketService.update(ticketUpdateRequest);
    }
}

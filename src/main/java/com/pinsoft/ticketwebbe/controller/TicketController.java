package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.dto.TicketRequest;
import com.pinsoft.ticketwebbe.dto.TicketUpdateRequest;
import com.pinsoft.ticketwebbe.entity.Ticket;
import com.pinsoft.ticketwebbe.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/ticket")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasAnyRole('ADMIN' , 'COMPANY_ADMIN' , 'COMPANY_USER')")
    public Ticket add(@RequestBody TicketUpdateRequest ticketUpdateRequest){
        return ticketService.update(ticketUpdateRequest);
    }
    @PutMapping("/ticket/cancel/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'COMPANY_ADMIN' , 'COMPANY_USER')")
    public Ticket cancelTicket(@RequestBody TicketUpdateRequest ticketUpdateRequest, @PathVariable Long id) {
        return ticketService.cancelTicket(ticketUpdateRequest, id);
    }
}

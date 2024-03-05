package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.TicketRequest;
import com.pinsoft.ticketwebbe.dto.TicketUpdateRequest;
import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.entity.Ticket;
import com.pinsoft.ticketwebbe.entity.User;
import com.pinsoft.ticketwebbe.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService extends AbstractBaseService<Ticket,Long> {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    BusNavigationService busNavigationService;

    @Autowired
    UserService userService;

    protected JpaRepository<Ticket, Long> getRepository() {
        return ticketRepository;
    }

    public Ticket save(TicketRequest ticketRequest){
        Ticket ticket = new Ticket();
        ticket.setActive(true);
        ticket.setCanceled(false);
        ticket.setPrice(ticketRequest.getPrice());
        ticket.setSeatInfo(ticketRequest.getSeatInfo());
        Optional<User> user = userService.getById(ticketRequest.getUserId());
        ticket.setUser(user.get());
        BusNavigation busNavigation = busNavigationService.get(ticketRequest.getBusNavigationId());
        ticket.setBusNavigation(busNavigation);
        return super.save(ticket);
    }
    public Ticket update(TicketUpdateRequest ticketUpdateRequest){
        Ticket ticket = ticketRepository.getReferenceById(ticketUpdateRequest.getId());
        ticket.setPrice(ticketUpdateRequest.getPrice());
        ticket.setSeatInfo(ticketUpdateRequest.getSeatInfo());
        ticket.setActive(ticketUpdateRequest.isActive());
        ticket.setCanceled(ticketUpdateRequest.isCanceled());
        User user= userService.getById(ticketUpdateRequest.getUserId()).get();
        ticket.setUser(user);
        BusNavigation busNavigation= busNavigationService.get(ticketUpdateRequest.getBusNavigationId());
        ticket.setBusNavigation(busNavigation);
        return ticketRepository.save(ticket);
    }
}

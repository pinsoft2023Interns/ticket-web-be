package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.TicketRequest;
import com.pinsoft.ticketwebbe.dto.TicketUpdateRequest;
import com.pinsoft.ticketwebbe.entity.Bus;
import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.entity.Ticket;
import com.pinsoft.ticketwebbe.entity.User;
import com.pinsoft.ticketwebbe.exceptions.ApiRequestException;
import com.pinsoft.ticketwebbe.repository.BusNavigationRepository;
import com.pinsoft.ticketwebbe.repository.TicketRepository;
import com.pinsoft.ticketwebbe.repository.UserRepository;
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
    BusNavigationRepository busNavigationRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    protected JpaRepository<Ticket, Long> getRepository() {
        return ticketRepository;
    }

    public Ticket save(TicketRequest ticketRequest){
        Ticket ticket = new Ticket();
        ticket.setActive(true);
        ticket.setCanceled(false);
        ticket.setPrice(ticketRequest.getPrice());
        ticket.setSeatInfo(ticketRequest.getSeatInfo());
        if(userRepository.findById(ticketRequest.getUserId()).isPresent() &&
        busNavigationRepository.findById(ticketRequest.getBusNavigationId()).isPresent()){
            Optional<User> user = userService.getById(ticketRequest.getUserId());
            BusNavigation busNavigation = busNavigationService.get(ticketRequest.getBusNavigationId());
            ticket.setBusNavigation(busNavigation);
            ticket.setUser(user.get());
            return super.save(ticket);
        }else{
            throw new ApiRequestException("Check busNavigation and user id again!");
        }

    }
    public Ticket update(TicketUpdateRequest ticketUpdateRequest){
        if(ticketRepository.findById(ticketUpdateRequest.getId()).isPresent()){
            Ticket ticket = ticketRepository.getReferenceById(ticketUpdateRequest.getId());
            ticket.setPrice(ticketUpdateRequest.getPrice());
            ticket.setSeatInfo(ticketUpdateRequest.getSeatInfo());
            ticket.setActive(ticketUpdateRequest.isActive());
            ticket.setCanceled(ticketUpdateRequest.isCanceled());
            if(userRepository.findById(ticketUpdateRequest.getUserId()).isPresent() &&
            busNavigationRepository.findById(ticketUpdateRequest.getBusNavigationId()).isPresent()){
                User user= userService.getById(ticketUpdateRequest.getUserId()).get();
                ticket.setUser(user);
                BusNavigation busNavigation= busNavigationService.get(ticketUpdateRequest.getBusNavigationId());
                ticket.setBusNavigation(busNavigation);
                return ticketRepository.save(ticket);
            }else{
                throw new ApiRequestException("Check user and busNavigation id again!");

            }

        }else {
            throw new ApiRequestException("the given id is not exist!");
        }

    }
}

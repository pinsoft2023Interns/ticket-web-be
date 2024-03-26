package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.TicketRequest;
import com.pinsoft.ticketwebbe.dto.TicketUpdateRequest;
import com.pinsoft.ticketwebbe.entity.BusNavStation;
import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.entity.Ticket;
import com.pinsoft.ticketwebbe.entity.User;
import com.pinsoft.ticketwebbe.enums.Permission;
import com.pinsoft.ticketwebbe.exceptions.ApiRequestException;
import com.pinsoft.ticketwebbe.repository.BusNavStationRepository;
import com.pinsoft.ticketwebbe.repository.BusNavigationRepository;
import com.pinsoft.ticketwebbe.repository.TicketRepository;
import com.pinsoft.ticketwebbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TicketService extends AbstractBaseService<Ticket,Long> {
    private final TicketRepository ticketRepository;

    private final BusNavigationService busNavigationService;

    private final BusNavigationRepository busNavigationRepository;

    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    BusNavStationService busNavStationService;

    @Autowired
    BusNavStationRepository busNavStationRepository;

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
                busNavigationRepository.findById(ticketRequest.getBusNavigationId()).isPresent()&&
                busNavStationRepository.findById(ticketRequest.getBusNavStatonId()).isPresent()){

            Optional<User> user = userService.getById(ticketRequest.getUserId());
            BusNavigation busNavigation = busNavigationService.get(ticketRequest.getBusNavigationId());
            BusNavStation busNavStation= busNavStationService.get(ticketRequest.getBusNavStatonId());

            if(user.get().getTickets().size() < 4) {
                ticket.setBusNavigation(busNavigation);
                ticket.setUser(user.get());
                ticket.setBusNavStation(busNavStation);
                return ticketRepository.save(ticket);
            }
            else {
                throw new ApiRequestException("One user can purchase up to four tickets!");
            }

        }
        else{
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
                    busNavigationRepository.findById(ticketUpdateRequest.getBusNavigationId()).isPresent()&&
                    busNavStationRepository.findById(ticketUpdateRequest.getBusNavStatonId()).isPresent()){
                User user= userService.getById(ticketUpdateRequest.getUserId()).get();
                ticket.setUser(user);
                BusNavigation busNavigation= busNavigationService.get(ticketUpdateRequest.getBusNavigationId());
                ticket.setBusNavigation(busNavigation);
                BusNavStation busNavStation= busNavStationService.get(ticketUpdateRequest.getBusNavStatonId());
                ticket.setBusNavStation(busNavStation);
                return ticketRepository.save(ticket);
            }
            else{
                throw new ApiRequestException("Check user and busNavigation id again!");

            }

        }
        else {
            throw new ApiRequestException("the given id is not exist!");
        }
    }

    public Ticket cancelTicket(TicketUpdateRequest ticketUpdateRequest, Long ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            LocalDateTime departureDate = ticket.getBusNavStation().getDepartureDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            LocalDateTime now = LocalDateTime.now();

            long minutesUntilDeparture = ChronoUnit.MINUTES.between(now, departureDate);

            Set<Permission> userPermissions = ticket.getUser().getRole().getPermissions();

            if (minutesUntilDeparture < 30 && userPermissions.contains(Permission.COMPANY_USER)) {
                throw new ApiRequestException("Biletin iptal edilmesi için izniniz yok.");
            }

            ticket.setCanceled(true);
            return ticketRepository.save(ticket);
        }
        else {
            throw new ApiRequestException("Belirtilen bilet bulunamadı!");
        }
    }
}

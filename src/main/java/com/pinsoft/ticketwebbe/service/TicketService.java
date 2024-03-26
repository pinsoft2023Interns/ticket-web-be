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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

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

    @Autowired
    BusNavStationService busNavStationService;

    @Autowired
    BusNavStationRepository busNavStationRepository;

    protected JpaRepository<Ticket, Long> getRepository() {
        return ticketRepository;
    }

    public Ticket save(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket();
        ticket.setActive(true);
        ticket.setCanceled(false);
        ticket.setPrice(ticketRequest.getPrice());
        ticket.setSeatInfo(ticketRequest.getSeatInfo());

        User user = userRepository.findById(ticketRequest.getUserId()).orElseThrow(() -> new ApiRequestException("Kullanıcı bulunamadı!"));
        BusNavigation busNavigation = busNavigationService.get(ticketRequest.getBusNavigationId());
        BusNavStation busNavStation= busNavStationService.get(ticketRequest.getBusNavStatonId());

        if (user != null && busNavigation != null && busNavStation != null) {
            Date departureDate = busNavStation.getDepartureDate();
            LocalDateTime twoDaysBeforeDeparture = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().minusDays(2);
            LocalDateTime now = LocalDateTime.now();

            if (now.isAfter(twoDaysBeforeDeparture)) {
                throw new ApiRequestException("Sefer tarihinden 2 gün öncesine kadar rezervasyon yapılabilir.");
            }

            if (user.getTickets().size() >= 4) {
                throw new ApiRequestException("One user can purchase up to four tickets!");
            }

            ticket.setUser(user);
            ticket.setBusNavigation(busNavigation);
            ticket.setBusNavStation(busNavStation);
            Ticket savedTicket = ticketRepository.save(ticket);

            if (!savedTicket.isActive()) {
                LocalDateTime departureDateTime = busNavStation.getDepartureDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime oneDayBeforeDeparture = departureDateTime.minusDays(1);
                LocalDateTime twoDaysAgo = LocalDateTime.now().minusDays(2);

                if (now.isAfter(oneDayBeforeDeparture)) {
                    System.out.println("Biletinizin kalkışına 1 gün kaldı!");
                }

                if (departureDateTime.isAfter(twoDaysAgo)) {
                    savedTicket.setCanceled(true);
                    ticketRepository.save(savedTicket);
                    throw new ApiRequestException("Rezervasyonunuz iptal edildi, çünkü satın alınmadı.");
                }
            }

            return savedTicket;
        } else {
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
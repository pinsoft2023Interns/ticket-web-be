package com.pinsoft.ticketwebbe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusNavStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int stationOrder;

    private Date arrivalDate;

    private Date departureDate;

    @ManyToOne
    @JoinColumn(name = "station_id")
    @JsonBackReference
    private Station station;

    @ManyToOne
    @JoinColumn(name = "bus_navigation_id")
    @JsonBackReference
    private BusNavigation busNavigation;

    @OneToMany(mappedBy = "busNavStation", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set <Ticket> tickets;

}

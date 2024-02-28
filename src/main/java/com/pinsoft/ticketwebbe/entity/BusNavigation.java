package com.pinsoft.ticketwebbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class BusNavigation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departurePlace;
    private String arrivalPlace;
    private Date departureDate;
    private float travelTime;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private Set<Station> stations;

    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private Set<Ticket> tickets;
}

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
    @JsonBackReference
    private Bus bus;

    @OneToMany(mappedBy = "id")
    @JsonManagedReference
    private Set<Station> stations;

    @OneToMany(mappedBy = "id")
    @JsonManagedReference
    private Set<Ticket> tickets;
}

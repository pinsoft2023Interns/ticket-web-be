package com.pinsoft.ticketwebbe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonBackReference
    private Bus bus;

    @OneToMany(mappedBy = "busNavigation")
    @JsonManagedReference
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "busNavigation")
    @JsonManagedReference
    private Set<BusNavStation> busNavStation;
}

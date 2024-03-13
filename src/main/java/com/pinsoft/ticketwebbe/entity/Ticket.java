package com.pinsoft.ticketwebbe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatInfo;
    private boolean isActive;
    private boolean isCanceled;
    private float price;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name= "bus_navigation_id")
    @JsonBackReference
    private BusNavigation busNavigation;



}

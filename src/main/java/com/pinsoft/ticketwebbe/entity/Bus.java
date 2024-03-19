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
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate;
    private String driverName;
    private String hostName;
    private String busDesign;
    private Integer numberOfSeats;

    @ManyToOne
    @JoinColumn(name= "company_id")
    @JsonBackReference
    private Company companyId;

    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<BusNavigation> busNavigation;

}

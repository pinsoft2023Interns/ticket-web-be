package com.pinsoft.ticketwebbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusNavigationUpdateRequest {
    private Long id;
    private String departurePlace;
    private String arrivalPlace;
    private Date departureDate;
    private float travelTime;
    private Long busId;
}
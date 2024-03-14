package com.pinsoft.ticketwebbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusNavStationRequest {
    private int order;
    private Date arrivalDate;

    private Date departureDate;

    private Long stationId;

    private Long busNavigationId;

}

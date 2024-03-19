package com.pinsoft.ticketwebbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusRequest {
    private String plate;
    private String driverName;
    private String hostName;
    private String busDesign;
    private Long companyId;
    private int numberOfSeats;
}

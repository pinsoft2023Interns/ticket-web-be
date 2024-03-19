package com.pinsoft.ticketwebbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private String seatInfo;
    private float price;
    private Long userId;
    private Long busNavigationId;
    private Long busNavStatonId;
}

package com.pinsoft.ticketwebbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketUpdateRequest {
    private Long id;
    private String seatInfo;
    private float price;
    private Long userId;
    private Long busNavigationId;
    private Long busNavStatonId;
    private boolean isActive;
    private boolean isCanceled;
}

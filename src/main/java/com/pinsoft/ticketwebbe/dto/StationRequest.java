package com.pinsoft.ticketwebbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationRequest {
    private String name;
    private Long busNavigationId;
}

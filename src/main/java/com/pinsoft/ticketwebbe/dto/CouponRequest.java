package com.pinsoft.ticketwebbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponRequest {
    private Long userId;
    private float amount;

}

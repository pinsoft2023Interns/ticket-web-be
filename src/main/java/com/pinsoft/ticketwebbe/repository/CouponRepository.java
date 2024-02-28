package com.pinsoft.ticketwebbe.repository;

import com.pinsoft.ticketwebbe.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}

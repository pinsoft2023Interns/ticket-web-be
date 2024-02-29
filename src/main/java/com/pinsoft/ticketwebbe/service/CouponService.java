package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.entity.Coupon;
import com.pinsoft.ticketwebbe.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponService extends AbstractBaseService<Coupon,Long> {
    @Autowired
    private CouponRepository couponRepository;

    @Override
    protected JpaRepository<Coupon, Long> getRepository() {
        return couponRepository;
    }


}

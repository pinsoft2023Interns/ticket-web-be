package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.CouponRequest;
import com.pinsoft.ticketwebbe.dto.CouponUpdateRequest;
import com.pinsoft.ticketwebbe.entity.Coupon;
import com.pinsoft.ticketwebbe.entity.User;
import com.pinsoft.ticketwebbe.repository.CouponRepository;
import com.pinsoft.ticketwebbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponService extends AbstractBaseService<Coupon,Long> {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserService userService;

    @Override
    protected JpaRepository<Coupon, Long> getRepository() {
        return couponRepository;
    }

    public Coupon save(CouponRequest couponRequest){
        Coupon coupon = new Coupon();
        coupon.setAmount(couponRequest.getAmount());
        User user = userService.getById(couponRequest.getUserId()).get();
        coupon.setUser(user);
        return super.save(coupon);
    }

    public Coupon update(CouponUpdateRequest couponUpdateRequest){
        Coupon coupon = couponRepository.getById(couponUpdateRequest.getId());
        coupon.setAmount(couponUpdateRequest.getAmount());
        User user = userService.getById(couponUpdateRequest.getUserId()).get();
        coupon.setUser(user);
        return couponRepository.save(coupon);
    }


}

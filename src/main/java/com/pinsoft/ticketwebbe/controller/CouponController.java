package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.dto.CouponRequest;
import com.pinsoft.ticketwebbe.entity.BusNavigation;
import com.pinsoft.ticketwebbe.entity.Coupon;
import com.pinsoft.ticketwebbe.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
public class CouponController {

    @Autowired
    CouponService couponService;

    @GetMapping("/coupon")
    public Collection<Coupon> get(){
        return couponService.listAll();
    }

    @GetMapping("/coupon/{id}")
    public Coupon get(@PathVariable Long id){
        return couponService.get(id);
    }

    @DeleteMapping("/coupon/{id}")
    public void delete(@PathVariable Long id){
        couponService.delete(id);
    }


    @PostMapping("/coupon")
    public Coupon add(@RequestBody CouponRequest couponRequest){
        return couponService.save(couponRequest);
    }


}

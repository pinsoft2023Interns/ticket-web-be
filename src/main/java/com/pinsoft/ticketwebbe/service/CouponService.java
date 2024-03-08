package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.CouponRequest;
import com.pinsoft.ticketwebbe.dto.CouponUpdateRequest;
import com.pinsoft.ticketwebbe.entity.Coupon;
import com.pinsoft.ticketwebbe.entity.User;
import com.pinsoft.ticketwebbe.exceptions.ApiRequestException;
import com.pinsoft.ticketwebbe.repository.CouponRepository;
import com.pinsoft.ticketwebbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouponService extends AbstractBaseService<Coupon,Long> {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected JpaRepository<Coupon, Long> getRepository() {
        return couponRepository;
    }

    public Coupon save(CouponRequest couponRequest){
        Coupon coupon = new Coupon();
        coupon.setAmount(couponRequest.getAmount());

        if(userRepository.findById(couponRequest.getUserId()).isPresent()){
            User user = userService.getById(couponRequest.getUserId()).get();
            coupon.setUser(user);
            return super.save(coupon);
        }
        else {
            throw new ApiRequestException("Check user id again!");
        }

    }

    public Coupon update(CouponUpdateRequest couponUpdateRequest){
        Optional<Coupon> couponRequest = couponRepository.findById(couponUpdateRequest.getId());
        if(couponRequest.isPresent()){
            Coupon coupon = couponRepository.findById(couponUpdateRequest.getId()).get();
            coupon.setAmount(couponUpdateRequest.getAmount());

            if(userRepository.findById(couponUpdateRequest.getUserId()).isPresent()){
                User user = userService.getById(couponUpdateRequest.getUserId()).get();
                coupon.setUser(user);
                return couponRepository.save(coupon);
            }
            else{
                throw new ApiRequestException("The user id is not valid!");
            }
        }
        else{
            throw new ApiRequestException("The given id is not exist!");
        }

    }


}

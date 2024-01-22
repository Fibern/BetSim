package com.example.betsim.data.local

import com.example.betsim.data.remote.responses.Coupon
import java.time.LocalDateTime

class CouponHolder {
    private var _coupon = Coupon(emptyList(), LocalDateTime.now(), 0.0, 0.0)
    fun saveCoupon(coupon: Coupon){
        _coupon = coupon
    }
    fun getCoupon(): Coupon{
        return _coupon
    }
}
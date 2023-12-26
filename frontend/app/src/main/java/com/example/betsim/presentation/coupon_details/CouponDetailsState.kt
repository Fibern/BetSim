package com.example.betsim.presentation.coupon_details

import com.example.betsim.domain.model.Coupon

data class CouponDetailsState (
    val coupon: Coupon? = null,
    val isLoaded: Boolean = false
)
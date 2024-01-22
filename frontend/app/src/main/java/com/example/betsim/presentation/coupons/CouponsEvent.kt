package com.example.betsim.presentation.coupons

import com.example.betsim.data.remote.responses.Coupon

sealed class CouponsEvent {
    data class ItemClicked(val coupon: Coupon): CouponsEvent()
}
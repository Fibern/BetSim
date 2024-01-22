package com.example.betsim.data.remote.responses

import com.google.gson.annotations.SerializedName

data class CouponsResponse(
    val errors: Errors,
    @SerializedName("message")
    val coupons: List<Coupon>,
    val succes: Boolean
)
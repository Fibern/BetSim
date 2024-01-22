package com.example.betsim.data.model

import com.example.betsim.data.remote.responses.Coupon
import java.time.LocalDate

data class Category(
    val header: LocalDate,
    val coupons: List<Coupon>
)
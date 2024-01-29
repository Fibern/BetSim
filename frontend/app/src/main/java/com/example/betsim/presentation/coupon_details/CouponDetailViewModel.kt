package com.example.betsim.presentation.coupon_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.data.local.CouponHolder
import com.example.betsim.data.remote.responses.Coupon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CouponDetailViewModel @Inject constructor(
    couponHolder: CouponHolder
): ViewModel(){

    private val _coupon = mutableStateOf(couponHolder.getCoupon())
    val coupon : State<Coupon> = _coupon

}
package com.example.betsim.presentation.coupons.components

import androidx.compose.runtime.Composable
import com.example.betsim.data.remote.responses.Coupon
import com.example.betsim.data.model.Category

sealed class CouponTabItem(val title:String, val screens: @Composable ()->Unit) {

    class InGame(coupons: List<Category>, onClick: (Coupon) -> Unit) : CouponTabItem(
        title = "Oczekujące",
        screens = {
            CouponsList(coupons, false){
                onClick(it)
            }
        }
    )

    class Finished(coupons: List<Category>, onClick: (Coupon) -> Unit): CouponTabItem(
        title = "Zakończone",
        screens = {
            CouponsList(coupons, true){
                onClick(it)
            }
        }
    )

}
package com.example.betsim.presentation.coupons.components

import androidx.compose.runtime.Composable
import com.example.betsim.presentation.coupons.Category

sealed class CouponTabItem(val title:String, val screens: @Composable ()->Unit) {

    class InGame(coupons: List<Category>, onClick: () -> Unit) : CouponTabItem(
        title = "Oczekujące",
        screens = {
            CouponsList(coupons, false){
                onClick()
            }
        }
    )

    class Finished(coupons: List<Category>, onClick: () -> Unit): CouponTabItem(
        title = "Zakończone",
        screens = {
            CouponsList(coupons, true){
                onClick()
            }
        }
    )

}
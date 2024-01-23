package com.example.betsim.presentation.coupons.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.runtime.Composable
import com.example.betsim.data.model.Category
import com.example.betsim.data.remote.responses.Coupon

sealed class CouponTabItem(val title:String, val screens: @Composable ()->Unit) {

    @OptIn(ExperimentalMaterialApi::class)
    class InGame(coupons: List<Category>, pullRefreshState: PullRefreshState, onClick: (Coupon) -> Unit) : CouponTabItem(
        title = "Oczekujące",
        screens = {
            CouponsList(coupons, false, pullRefreshState){
                onClick(it)
            }
        }
    )

    @OptIn(ExperimentalMaterialApi::class)
    class Finished(coupons: List<Category>, pullRefreshState: PullRefreshState, onClick: (Coupon) -> Unit): CouponTabItem(
        title = "Zakończone",
        screens = {
            CouponsList(coupons, true, pullRefreshState){
                onClick(it)
            }
        }
    )

}
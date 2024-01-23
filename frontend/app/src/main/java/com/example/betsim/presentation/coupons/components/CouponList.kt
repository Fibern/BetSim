package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.betsim.data.model.Category
import com.example.betsim.data.remote.responses.Coupon

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun CouponsList(coupons: List<Category>, finished: Boolean, pullRefreshState: PullRefreshState, onClick: (Coupon) -> Unit){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            coupons.forEach { category ->

                if (category.coupons.any { /*TODO() it.finished == finished */ true }) {

                    stickyHeader {
                        CouponHeader(category = category)
                    }

                    items(category.coupons) {
                        if ( /* TODO() it.finished == finished */ true) {
                            CouponListItem(it, Modifier.clickable {
                                onClick(it)
                            })
                        }
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = false,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )

    }

}
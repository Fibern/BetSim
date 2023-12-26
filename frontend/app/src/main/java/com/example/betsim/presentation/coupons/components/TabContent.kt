package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabContent(items: List<CouponTabItem>, pagerState: PagerState){

    HorizontalPager(state = pagerState) {page ->
        items[page].screens()
    }

}
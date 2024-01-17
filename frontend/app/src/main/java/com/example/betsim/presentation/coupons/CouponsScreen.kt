package com.example.betsim.presentation.coupons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.betsim.presentation.util.Screen
import com.example.betsim.presentation.coupons.components.CouponTabItem
import com.example.betsim.presentation.coupons.components.CouponTabs
import com.example.betsim.presentation.coupons.components.TabContent
import com.example.betsim.presentation.main.MainEvent
import com.example.betsim.presentation.main.MainViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CouponsScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    viewModel: CouponsScreenViewModel = hiltViewModel()
) {

    val coupons by remember { viewModel.coupons }

    val onClick: () -> Unit = {
        navController.navigate(Screen.CouponDetailsScreen.route)
        mainViewModel.onEvent(MainEvent.HiddenChange(true))
    }

    val items = listOf(
        CouponTabItem.InGame(coupons, onClick),
        CouponTabItem.Finished(coupons, onClick)
    )
    val pagerState = rememberPagerState( pageCount={items.size}, initialPage = 0 )


    Column{
        CouponTabs(items, pagerState)
        TabContent(items, pagerState)
    }

}


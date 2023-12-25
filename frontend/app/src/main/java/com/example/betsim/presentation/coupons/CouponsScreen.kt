package com.example.betsim.presentation.coupons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.betsim.presentation.Screen
import com.example.betsim.presentation.coupons.components.TabContent
import com.example.betsim.presentation.coupons.components.TabItem
import com.example.betsim.presentation.coupons.components.Tabs
import com.example.betsim.presentation.user_main.UserMainEvent
import com.example.betsim.presentation.user_main.UserMainViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CouponsScreen(
    mainViewModel: UserMainViewModel,
    navController: NavController,
    viewModel: CouponsScreenViewModel = hiltViewModel()
) {

    val onClick: () -> Unit = {
        navController.navigate(Screen.CouponDetailsScreen.route)
        mainViewModel.onEvent(UserMainEvent.AppBarsChange(true))
        mainViewModel.onEvent(UserMainEvent.HiddenChange(true))
    }

    val items = listOf(
        TabItem.InGame(viewModel.coupons, onClick),
        TabItem.Finished(viewModel.coupons, onClick)
    )
    val pagerState = rememberPagerState( pageCount={items.size}, initialPage = 0 )


    Column{
        Tabs(pagerState, items)
        TabContent(items, pagerState)
    }

}


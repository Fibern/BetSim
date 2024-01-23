package com.example.betsim.presentation.coupons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.betsim.data.remote.responses.Coupon
import com.example.betsim.presentation.common.components.SemiTransparentLoadingScreen
import com.example.betsim.presentation.coupons.components.CouponTabItem
import com.example.betsim.presentation.coupons.components.CouponTabs
import com.example.betsim.presentation.coupons.components.TabContent
import com.example.betsim.presentation.main.MainEvent
import com.example.betsim.presentation.main.MainViewModel
import com.example.betsim.presentation.util.Screen


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun CouponsScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    viewModel: CouponsScreenViewModel = hiltViewModel()
) {

    val coupons = viewModel.coupons
    val isLoading by remember { viewModel.isLoading }

    val onClick: (Coupon) -> Unit = {
        navController.navigate(Screen.CouponDetailsScreen.route)
        viewModel.onEvent(CouponsEvent.ItemClicked(it))
        mainViewModel.onEvent(MainEvent.HiddenChange(true))
    }

    val pull = rememberPullRefreshState(refreshing = isLoading, onRefresh = {
        viewModel.onEvent(
            CouponsEvent.Refresh
        )
    } )


    val items = listOf(
        CouponTabItem.InGame(coupons, pull, onClick),
        CouponTabItem.Finished(coupons, pull, onClick)
    )
    val pagerState = rememberPagerState( pageCount={items.size}, initialPage = 0 )


    Column{
        CouponTabs(items, pagerState)
        TabContent(items, pagerState)
    }
    if (isLoading)
        SemiTransparentLoadingScreen()

}


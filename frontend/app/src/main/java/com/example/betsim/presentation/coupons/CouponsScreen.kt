package com.example.betsim.presentation.coupons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.betsim.presentation.coupons.components.TabContent
import com.example.betsim.presentation.coupons.components.TabItem
import com.example.betsim.presentation.coupons.components.Tabs


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CouponsScreen(
    navController: NavController,
    viewModel: CouponsScreenViewModel = hiltViewModel()
) {

    val items = listOf(
        TabItem.InGame(viewModel.coupons, navController), TabItem.Finished(viewModel.coupons, navController)
    )
    val pagerState = rememberPagerState( pageCount={items.size}, initialPage = 0 )


    Column{
        Tabs(pagerState, items)
        TabContent(items, pagerState)
    }

}
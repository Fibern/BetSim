package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CouponTabs(items: List<CouponTabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    PrimaryTabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        indicator = {
            TabRowDefaults.PrimaryIndicator(
                modifier = Modifier
                    .tabIndicatorOffset(
                        selectedTabIndex = pagerState.currentPage,
                        matchContentSize = true
                    ),
                width = Dp.Unspecified,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    ) {
        items.forEachIndexed{index, item ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    } },
                text = { Text(item.title, color = MaterialTheme.colorScheme.onPrimary) }
            )
        }
    }
}
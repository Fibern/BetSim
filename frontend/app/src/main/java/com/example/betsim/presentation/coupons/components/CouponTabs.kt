package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CouponTabs(items: List<CouponTabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        indicator = {tabPositions ->
            Box(
                modifier = Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .height(3.dp)
                    .padding(horizontal = 72.dp)
                    .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
                    .background(MaterialTheme.colorScheme.onPrimary)
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
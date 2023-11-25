package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pagerState: PagerState, items: List<TabItem>) {
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
                text = { Text(item.title) }
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabContent(items: List<TabItem>, pagerState: PagerState){

    HorizontalPager(state = pagerState) {page ->
        items[page].screens()
    }

}


@Composable
fun CouponListItem(icon: ImageVector) {
    ListItem(
        headlineContent = { Text("Polska") },
        supportingContent = { Text("15:23")},
        trailingContent = { Text("+25") },
        leadingContent = {
            Icon(
                icon,
                contentDescription = "Localized description",
            )
        }
    )
    Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))

}



@Composable
fun CouponsList(color: Color){

    val couponList = listOf(1..20)

    LazyColumn{

        items(20) {
            CouponListItem(Icons.Default.HourglassTop)
        }

    }

}



typealias ComposableFun = @Composable ()->Unit
sealed class TabItem(val title:String, val screens:ComposableFun) {

    object InGame : TabItem(title = "Oczekujące", screens = { CouponsList(Color.Magenta) })
    object Ended: TabItem(title = "Zakończone", screens={ CouponsList(Color.Cyan) })


}
package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.model.Coupon
import com.example.betsim.presentation.coupons.Category
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

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
                text = { Text(item.title, color = MaterialTheme.colorScheme.onPrimary) }
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
fun CouponListItem(coupon: Coupon) {

    val text = if (coupon.games.size > 1) {
        "Kupon łączony"
    } else {
        coupon.games[0].odds[coupon.games[0].selected.value!!].name
    }

    var icon = Icons.Default.HourglassTop
    var color = Color.Yellow
    var winnings = coupon.winnings.toString()
    if(coupon.finished) {
        winnings = "+$winnings"
        if (coupon.winnings == 0.0) {
            icon = Icons.Default.Cancel
            color = Color.Red
        } else {
            icon = Icons.Default.CheckCircle
            color = Color.Green
        }
    }

    ListItem(
        headlineContent = { Text(text) },
        supportingContent = { Text(DateTimeFormatter.ofPattern("hh:mm:ss").format(coupon.date),)},
        trailingContent = { Text(winnings) },
        leadingContent = {
            Icon(
                icon,
                tint = color,
                contentDescription = ""
            )
        }
    )
    Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))

}

@Composable
fun CouponHeader(
    category: Category
){
    Row(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.onPrimary).padding(vertical = 8.dp)) {
        Text(
            text = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(category.header),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CouponsList(coupons: List<Category>, finished: Boolean){

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){

        coupons.forEach {category ->

            if (category.coupons.any { it.finished == finished }) {

                stickyHeader {
                    CouponHeader(category = category)
                }

                items(category.coupons) {
                    if (it.finished == finished) {
                        CouponListItem(it)
                    }
                }
            }
        }

    }

}


sealed class TabItem(val title:String, val screens: @Composable ()->Unit) {

    class InGame(coupons: List<Category>) : TabItem(
        title = "Oczekujące",
        screens = {
            CouponsList(coupons, false)
        }
    )


    class Finished(coupons: List<Category>): TabItem(
        title = "Zakończone",
        screens = {
            CouponsList(coupons, true)
        }
    )


}
package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.model.Coupon
import com.example.betsim.presentation.common.components.CouponStatusIcon
import com.example.betsim.presentation.coupons.Category
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.flow.MutableStateFlow
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
fun CouponListItem(coupon: Coupon, modifier: Modifier) {

    val text =
        if (coupon.games.size > 1) "Kupon łączony"
        else coupon.games[0].odds[coupon.games[0].selected.value!!].name


    val winnings =
        if (coupon.finished) "+${coupon.winnings}"
        else coupon.winnings.toString()

    ListItem(
        headlineContent = { Text(text) },
        supportingContent = { Text(DateTimeFormatter.ofPattern("HH:mm:ss").format(coupon.date))},
        trailingContent = { Text(winnings) },
        leadingContent = {
             if (coupon.finished){
                 if (coupon.winnings == 0.0) CouponStatusIcon.LoseIcon()
                 else CouponStatusIcon.WinIcon()
             } else CouponStatusIcon.AwaitIcon()
        },
        modifier = modifier
    )
    Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))

}

@Composable
fun CouponHeader(
    category: Category
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.onPrimary)
        .padding(vertical = 8.dp)) {
        Text(
            text = DateTimeFormatter.ofPattern("yyyy.MM.dd").format(category.header),
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
fun CouponsList(coupons: MutableStateFlow<List<Category>>, finished: Boolean, onClick: () -> Unit){

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){

        coupons.value.forEach {category ->

            if (category.coupons.any { it.finished == finished }) {

                stickyHeader {
                    CouponHeader(category = category)
                }

                items(category.coupons) {
                    if (it.finished == finished) {
                        CouponListItem(it, Modifier.clickable {
                            onClick()
                        })
                    }
                }
            }
        }

    }

}


sealed class TabItem(val title:String, val screens: @Composable ()->Unit) {

    class InGame(coupons: MutableStateFlow<List<Category>>, onClick: () -> Unit) : TabItem(
        title = "Oczekujące",
        screens = {
            CouponsList(coupons, false){
                onClick()
            }
        }
    )


    class Finished(coupons: MutableStateFlow<List<Category>>, onClick: () -> Unit): TabItem(
        title = "Zakończone",
        screens = {
            CouponsList(coupons, true){
                onClick()
            }
        }
    )


}
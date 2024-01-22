package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.betsim.data.remote.responses.Coupon
import com.example.betsim.data.model.Category

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CouponsList(coupons: List<Category>, finished: Boolean, onClick: (Coupon) -> Unit){

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){

        coupons.forEach {category ->

            if (category.coupons.any { /*TODO() it.finished == finished */ true}) {

                stickyHeader {
                    CouponHeader(category = category)
                }

                items(category.coupons) {
                    if ( /* TODO() it.finished == finished */ true) {
                        CouponListItem(it, Modifier.clickable {
                            onClick(it)
                        })
                    }
                }
            }
        }

    }

}
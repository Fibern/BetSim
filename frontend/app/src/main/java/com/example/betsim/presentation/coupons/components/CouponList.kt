package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.betsim.presentation.coupons.Category

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CouponsList(coupons: List<Category>, finished: Boolean, onClick: () -> Unit){

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
                        CouponListItem(it, Modifier.clickable {
                            onClick()
                        })
                    }
                }
            }
        }

    }

}
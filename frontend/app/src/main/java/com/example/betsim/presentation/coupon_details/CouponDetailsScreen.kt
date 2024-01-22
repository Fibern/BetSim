package com.example.betsim.presentation.coupon_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.coupon_details.components.CouponDetailsBottomBar
import com.example.betsim.presentation.common.components.BetSimSubsidiaryTopBar
import com.example.betsim.presentation.common.components.CouponStatus

@Composable
fun CouponDetailsScreen(
    navController: NavHostController,
    viewModel: CouponDetailViewModel = hiltViewModel()
){

    val coupon by remember { viewModel.coupon }

    Scaffold(
        topBar = { BetSimSubsidiaryTopBar(
            "Twój kupon"
        ){
            navController.navigateUp()
        } },
        bottomBar = {
            CouponDetailsBottomBar(
                coupon.oddSum,
                coupon.value,
                0.0, // TODO ()couponState.coupon!!.winnings,
                false // TODO() couponState.coupon!!.finished
            )
        }
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            LazyColumn {
                items(coupon.bets) { bets ->
                    val odd = bets.prediction
                    ListItem(
                        headlineContent = { bets.title },
                        leadingContent = {
                             CouponStatus.entries[bets.status].GetIcon()
                        },
                        supportingContent = { Text(text = odd.playerName) },
                        trailingContent = {
                            Text(text = odd.oddValue.toString().replace('.',','))
                        },
                        overlineContent = {
                            /*TODO()
                            Text(
                                text = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    .format(bet.date)
                            )
                             */
                        }
                    )
                }
            }
        }

    }

}




@Preview
@Composable
fun ScreenCouponDetailsScreenPreview(){

    val navController = rememberNavController()
    CouponDetailsScreen(navController)

}
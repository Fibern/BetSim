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
import com.example.betsim.presentation.common.components.CouponStatusIcon
import com.example.betsim.presentation.coupon_details.components.CouponDetailsBottomBar
import com.example.betsim.presentation.coupon_details.components.CouponDetailsTopBar
import com.example.betsim.presentation.main.MainEvent
import com.example.betsim.presentation.main.MainViewModel
import java.time.format.DateTimeFormatter

@Composable
fun CouponDetailsScreen(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    viewModel: CouponDetailViewModel = hiltViewModel()
){

    val couponState by remember {
        viewModel.coupon
    }

    Scaffold(
        topBar = { CouponDetailsTopBar{
            mainViewModel.onEvent(MainEvent.AppBarsChange(false))
            navController.navigateUp()
        } },
        bottomBar = {
            if (couponState.isLoaded) {
                CouponDetailsBottomBar(couponState.coupon!!.odd, couponState.coupon!!.betValue, couponState.coupon!!.winnings, couponState.coupon!!.finished)
            }
        }
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            if (couponState.isLoaded) {

                LazyColumn {
                    items(couponState.coupon!!.games){ tournamentGame ->
                        val index = tournamentGame.selected.value!!
                        val odd = tournamentGame.odds[index]
                        ListItem(
                            headlineContent = { Text(text = "${tournamentGame.homeTeam}-${tournamentGame.awayTeam}")},
                            leadingContent = {
                                when (tournamentGame.status) {
                                    "lost" -> CouponStatusIcon.LoseIcon()
                                    "won" -> CouponStatusIcon.WinIcon()
                                    else -> CouponStatusIcon.AwaitIcon()
                                } },
                            supportingContent = { Text(text = odd.name)},
                            trailingContent = {
                                Text(text = odd.odd.toString())
                            },
                            overlineContent = {
                                Text(
                                    text = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                        .format(tournamentGame.date)
                                )
                            }
                        )
                    }
                }
            }


        }
    }

}




@Preview
@Composable
fun ScreenCouponDetailsScreenPreview(){

    val navController = rememberNavController()
    val viewModel = MainViewModel()
    CouponDetailsScreen(viewModel, navController)

}
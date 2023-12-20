package com.example.betsim.presentation.coupon_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.common.components.CouponStatusIcon
import com.example.betsim.presentation.user_main.UserMainEvent
import com.example.betsim.presentation.user_main.UserMainViewModel
import java.time.format.DateTimeFormatter

@Composable
fun CouponDetailsScreen(
    mainViewModel: UserMainViewModel,
    navController: NavHostController,
    viewModel: CouponDetailViewModel = hiltViewModel()
){

    val couponState by remember {
        viewModel.coupon
    }

    Scaffold(
        topBar = { TopCouponBar{
            mainViewModel.onEvent(UserMainEvent.AppBarsChange(false))
            navController.navigateUp()
        } },
        bottomBar = {
            if (couponState.isLoaded) {
                BottomCouponBar(couponState.coupon!!.odd, couponState.coupon!!.betValue, couponState.coupon!!.winnings, couponState.coupon!!.finished)
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
    val viewModel = UserMainViewModel()
    CouponDetailsScreen(viewModel, navController)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopCouponBar(onClick: () -> Unit){

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        },
        title = { Text("Twój kupon") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )

}

@Composable
fun BottomCouponBar(odd: Double, value: Double, winnings: Double, finished: Boolean){

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primary,
                headlineColor =  MaterialTheme.colorScheme.onPrimary,
                trailingIconColor = MaterialTheme.colorScheme.onPrimary
            ),
            headlineContent = { Text(text = "Łączny kurs") },
            trailingContent = { Text(text = odd.toString()) }
        )
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primary,
                headlineColor =  MaterialTheme.colorScheme.onPrimary,
                trailingIconColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            headlineContent = { Text(text = "Stawka") },
            trailingContent = { Text(text = value.toString()) }
        )
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primary,
                headlineColor =  MaterialTheme.colorScheme.onPrimary,
                trailingIconColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            headlineContent = { Text(text = if(finished) "Wygrana" else "Potencjalna wygrana") },
            trailingContent = { Text(text = winnings.toString()) }
        )

    }

}
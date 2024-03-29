package com.example.betsim.presentation.offers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.betsim.presentation.common.components.BlankScreenItem
import com.example.betsim.presentation.common.components.SemiTransparentLoadingScreen
import com.example.betsim.presentation.main.MainEvent
import com.example.betsim.presentation.main.MainViewModel
import com.example.betsim.presentation.offers.components.OffersItem
import com.example.betsim.presentation.common.util.Screen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OffersScreen(
    viewModel: OffersViewModel = hiltViewModel(),
    navController: NavController,
    mainViewModel: MainViewModel,
) {

    val offers = viewModel.offers
    val coupon by remember { mainViewModel.couponState }
    val isMod by remember { viewModel.isMod }
    val route by remember { viewModel.route }
    val id by remember { viewModel.id }
    val isLoading by remember { viewModel.isLoading }

    val pull = rememberPullRefreshState(refreshing = isLoading, onRefresh = {
        viewModel.onEvent(
            OffersEvent.Refresh
        )
    } )

    Scaffold(
        floatingActionButton = {
            if (isMod && id != -1) {
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = {
                        navController.navigate(route)
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 500.dp, start = 200.dp)
                ) {
                    Icon(Icons.Filled.Add, "add", tint = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    ) {paddingValues ->


        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            if (offers.isEmpty())
                BlankScreenItem(text = "Brak ofert")

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pull)
            ) {

                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {

                    itemsIndexed(offers) { index, offer ->
                        val i = coupon.offers.indexOf(offer)
                        if (i != -1) viewModel.onEvent(
                            OffersEvent.LoadList(
                                coupon.offers[i],
                                index
                            )
                        )
                        OffersItem(offer, isMod) { onClickIndex ->
                            if (isMod) {
                                viewModel.onEvent(OffersEvent.ModifyClicked(index))
                                navController.navigate("${Screen.ModifyGameScreenDefault.route}?id=${id}")
                            } else {
                                viewModel.onEvent(OffersEvent.OnSelect(offer, onClickIndex))
                                mainViewModel.onEvent(MainEvent.AddGame(offer))
                            }
                        }
                    }

                }

                PullRefreshIndicator(
                    refreshing = false,
                    state = pull,
                    modifier = Modifier.align(Alignment.TopCenter),
                )
            }
            if (isLoading)
                SemiTransparentLoadingScreen()

        }
    }
}
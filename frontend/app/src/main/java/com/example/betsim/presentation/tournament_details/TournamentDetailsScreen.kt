package com.example.betsim.presentation.tournament_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.betsim.presentation.main.MainViewModel
import com.example.betsim.presentation.tournament_details.components.TournamentDetailChoice
import com.example.betsim.presentation.util.Screen

@Composable
fun TournamentDetailScreen(
    viewModel: TournamentDetailsViewModel = hiltViewModel(),
    navController: NavController,
    mainViewModel: MainViewModel,
) {

    val offers = viewModel.offers
    val coupon by remember { mainViewModel.couponState }
    val isMod by remember { viewModel.isMod }
    val route by remember { viewModel.route }

    Scaffold(
        floatingActionButton = {
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
    ) {paddingValues ->


        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {


            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                itemsIndexed(offers) { index, offer ->
                    /* TODO()
                    val i = coupon.games.indexOf(offer)
                    if (i != -1) viewModel.onEvent(
                        TournamentDetailsEvent.LoadList(
                            coupon.games[i],
                            index
                        )
                    )
                     */

                    TournamentDetailChoice(offer, isMod) { onClickIndex ->
                        if (isMod) {
                            navController.navigate(Screen.ModifyGameScreen.route)
                        } else {
                            viewModel.onEvent(TournamentDetailsEvent.OnSelect(offer, onClickIndex))
                            // TODO() mainViewModel.onEvent(MainEvent.AddGame(game))
                        }
                    }
                }

            }

        }
    }
}
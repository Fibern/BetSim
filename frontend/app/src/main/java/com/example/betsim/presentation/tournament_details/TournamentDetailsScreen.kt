package com.example.betsim.presentation.tournament_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.betsim.presentation.Screen
import com.example.betsim.presentation.main.MainEvent
import com.example.betsim.presentation.main.MainViewModel
import com.example.betsim.presentation.tournament_details.components.TournamentDetailChoice

@Composable
fun TournamentDetailScreen(
    viewModel: TournamentDetailsViewModel = hiltViewModel(),
    navController: NavController,
    mainViewModel: MainViewModel,
){

    val state by remember { viewModel.state }
    val coupon by remember { mainViewModel.couponState }
    val isMod = viewModel.isMod

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {


        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){

            itemsIndexed(state.games){index, game->
                val i = coupon.games.indexOf(game)
                if (i != -1) viewModel.onEvent(TournamentDetailsEvent.LoadList(coupon.games[i], index))

                TournamentDetailChoice(game, isMod) { onClickIndex ->
                    if (isMod) {
                        navController.navigate(Screen.ModifyGameScreen.route)
                    } else {
                        viewModel.onEvent(TournamentDetailsEvent.OnSelect(game, onClickIndex))
                        mainViewModel.onEvent(MainEvent.AddGame(game))
                    }
                }
            }

        }

    }

}
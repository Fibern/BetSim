package com.example.betsim.presentation.tournament_details_user

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
import com.example.betsim.presentation.main.MainEvent
import com.example.betsim.presentation.main.MainViewModel
import com.example.betsim.presentation.tournament_details_user.components.TournamentDetailChoice

@Composable
fun TournamentDetailScreen(
    viewModel: TournamentDetailsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
){

    val state by remember { viewModel.state }
    val coupon by remember { mainViewModel.couponState }

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

                TournamentDetailChoice(game){oddIndex ->
                    viewModel.onEvent(TournamentDetailsEvent.OnSelect(game, oddIndex))
                    mainViewModel.onEvent(MainEvent.AddGame(game))
                }
            }

        }

    }

}
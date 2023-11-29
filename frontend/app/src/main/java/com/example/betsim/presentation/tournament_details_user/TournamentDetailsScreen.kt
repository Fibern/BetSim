package com.example.betsim.presentation.tournament_details_user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.betsim.presentation.tournament_details_user.components.TournamentDetailChoice
import com.example.betsim.presentation.user_main.UserMainViewModel

@Composable
fun TournamentDetailScreen(
    viewModel: TournamentDetailsViewModel = hiltViewModel(),
    mainViewModel: UserMainViewModel,
){

    val state = viewModel.state.value


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {


        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){

            itemsIndexed(state.games){index, game->
                val list = mainViewModel.state.value.games
                val ind = list.indexOf(game)
                if (ind != -1){
                    state.games[ind] = list[ind]
                }
                TournamentDetailChoice(game){
                    if(it !in mainViewModel.state.value.games) mainViewModel.state.value.games.add(it)
                }
            }

        }


    }

}
package com.example.betsim.presentation.tournaments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.tournaments.components.TournamentItem


@Composable
fun TournamentsScreen(
    viewModel: TournamentsScreenViewModel = hiltViewModel(),
    navController: NavController
){

    val state by remember { viewModel.state }
    val route by remember { viewModel.route }
    val isMod by remember { viewModel.isMod }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ){
            items(state.tournaments){tournament ->
                    TournamentItem(
                        tournament,
                        isMod,
                        Modifier.clickable(onClick = {
                            navController.navigate(route)
                        })
                    ){
                        //TODO()
                    }
            }
        }

    }

}


@Composable
@Preview
fun TournamentsScreenPreview() {
    val map =   mapOf(
        "mod" to false,
        "today" to false
    )
    val viewModel = remember {
        TournamentsScreenViewModel(SavedStateHandle(map))
    }

    val fakeNavController = rememberNavController()

    TournamentsScreen(
        viewModel = viewModel,
        navController = fakeNavController
    )
}
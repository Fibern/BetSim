package com.example.betsim.presentation.user_main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.betsim.presentation.Screen
import com.example.betsim.presentation.coupons.CouponsScreen
import com.example.betsim.presentation.leaderboard.LeaderboardScreen
import com.example.betsim.presentation.profile.Profile
import com.example.betsim.presentation.tournament_details_user.TournamentDetailScreen
import com.example.betsim.presentation.tournaments_user.TournamentsUserScreen
import com.example.betsim.presentation.user_main.components.BetSimBottomAppBar
import com.example.betsim.presentation.user_main.components.BetSimTopAppBar
import com.example.betsim.presentation.user_main.components.FloatingABAnimation
import com.example.betsim.presentation.user_main.components.OpenedCouponFog


@Composable
fun UserMainScreen(
    viewModel: UserMainViewModel = hiltViewModel()
){

    val state = viewModel.state.value

    val navController = rememberNavController()

    var collapsed by remember{
        mutableStateOf(true)
    }

    var hidden by remember{
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) { collapsed = true },

        topBar = { BetSimTopAppBar() },
        floatingActionButton = {
            FloatingABAnimation(hidden = hidden, collapsed = collapsed, games = state.games) {
            collapsed = false
        } },
        bottomBar = {
            BetSimBottomAppBar(navController){
            hidden = it
            collapsed = true
        } }


    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "all",
            Modifier.padding(innerPadding)
        ){
            navigation(startDestination = Screen.TodayTournamentsScreen.route, route = "today"){
                composable(Screen.TodayTournamentsScreen.route,
                    arguments = listOf(
                        navArgument("today"){
                            type = NavType.BoolType
                            defaultValue = true
                        }
                    )
                ) { TournamentsUserScreen(navController = navController) }
                composable(Screen.TodayTournamentDetailScreen.route) { TournamentDetailScreen(mainViewModel = viewModel) }
            }
            navigation(startDestination = Screen.TournamentsScreen.route, route = "all"){
                composable(Screen.TournamentsScreen.route,
                    arguments = listOf(
                        navArgument("today"){
                            type = NavType.BoolType
                            defaultValue = false
                        }
                    )
                ) { TournamentsUserScreen(navController = navController) }
                composable(Screen.TournamentDetailScreen.route) { TournamentDetailScreen(mainViewModel = viewModel) }
            }
            composable(Screen.ProfileScreen.route) { Profile() }
            composable(Screen.CouponsScreen.route) { CouponsScreen() }
            composable(Screen.LeaderboardScreen.route) { LeaderboardScreen() }

        }

        OpenedCouponFog(padding = innerPadding, active = !collapsed) {
            collapsed = true
        }

    }



}

@Preview
@Composable
fun UserMainPreview(){
    UserMainScreen()
}
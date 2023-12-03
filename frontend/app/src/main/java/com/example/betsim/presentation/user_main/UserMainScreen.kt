package com.example.betsim.presentation.user_main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    val navController = rememberNavController()



    Scaffold(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
              viewModel.onEvent(UserMainEvent.CollapsedChange(true))
            },

        topBar = { BetSimTopAppBar() },
        floatingActionButton = {
            FloatingABAnimation(
                hidden = viewModel.couponHidden.value,
                collapsed = viewModel.couponCollapsed.value,
                games = viewModel.games,
                oddsValue = "%.2f".format(viewModel.oddValue.doubleValue),
                text = viewModel.bet.value,
                onClick = {
                          viewModel.onEvent(UserMainEvent.CollapsedChange(false))
                },
                onDeleteClick = { game ->
                    viewModel.onEvent(UserMainEvent.DeleteGame(game))
                },
                onValueChange = {
                    viewModel.onEvent(UserMainEvent.EnteredValue(it))
                }
            ) },
        bottomBar = {
            BetSimBottomAppBar(navController){
                viewModel.onEvent(UserMainEvent.HiddenChange(it))
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

        OpenedCouponFog(padding = innerPadding, active = !viewModel.couponCollapsed.value) {
            viewModel.onEvent(UserMainEvent.CollapsedChange(true))
        }

    }



}

@Preview
@Composable
fun UserMainPreview(){
    UserMainScreen()
}
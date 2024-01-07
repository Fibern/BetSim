package com.example.betsim.presentation.main.components.nav_hosts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.betsim.presentation.util.Screen
import com.example.betsim.presentation.create_feature.CreateEventScreen
import com.example.betsim.presentation.create_feature.CreateGameMainScreen
import com.example.betsim.presentation.main.MainViewModel
import com.example.betsim.presentation.modify.ModifyScreen
import com.example.betsim.presentation.profile.Profile
import com.example.betsim.presentation.tournament_details.TournamentDetailScreen
import com.example.betsim.presentation.tournaments.TournamentsScreen

@Composable
fun ModNavHost(viewModel: MainViewModel, navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.EventsNav.route,
        modifier = Modifier.padding(paddingValues)
    ){

        navigation(
            startDestination = Screen.TournamentsScreen.route,
            route = Screen.EventsNav.route
        ){
            
            composable(
                route = Screen.TournamentsScreen.route,
                arguments = listOf(
                    navArgument("today"){
                        type = NavType.BoolType
                        defaultValue = false
                    },
                    navArgument("mod"){
                        type = NavType.BoolType
                        defaultValue = true
                    }
                )
            ){ TournamentsScreen(navController = navController) }

            composable(
                Screen.TournamentDetailScreen.route,
                arguments = listOf(
                    navArgument("today"){
                        type = NavType.BoolType
                        defaultValue = false
                    },
                    navArgument("mod"){
                        type = NavType.BoolType
                        defaultValue = true
                    }
                )
            ) { TournamentDetailScreen(mainViewModel = viewModel , navController = navController) }

            composable(route = Screen.AddTournamentScreen.route){ CreateEventScreen(navController) }
            composable(route = Screen.AddGameMainScreen.route){ CreateGameMainScreen(navController) }
        }
        composable(
            Screen.StartedGamesScreen.route,
            arguments = listOf(
                navArgument("today"){
                    type = NavType.BoolType
                    defaultValue = true
                },
                navArgument("mod"){
                    type = NavType.BoolType
                    defaultValue = true
                }
            )
        ){ TournamentDetailScreen(mainViewModel = viewModel, navController = navController) }
        composable(route = Screen.ModifyGameScreen.route){ ModifyScreen(navController) }
        composable(route = Screen.ProfileScreen.route){ Profile() }

    }
}
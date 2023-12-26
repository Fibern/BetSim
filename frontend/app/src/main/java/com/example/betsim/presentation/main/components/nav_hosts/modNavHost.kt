package com.example.betsim.presentation.main.components.nav_hosts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.betsim.presentation.Screen
import com.example.betsim.presentation.main.MainViewModel
import com.example.betsim.presentation.profile.Profile

@Composable
fun ModNavHost(viewModel: MainViewModel, navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.EventsNav.route,
        modifier = Modifier.padding(paddingValues)
    ){

        navigation(
            startDestination = Screen.EventScreen.route,
            route = Screen.EventsNav.route
        ){
            composable(route = Screen.EventScreen.route){}
            composable(route = Screen.AddEventScreen.route){}
            composable(route = Screen.EventDetailScreen.route){}
            composable(route = Screen.AddEventGameScreen.route){}
        }
        navigation(
            startDestination = Screen.StartedGamesScreen.route,
            route = Screen.StartedGamesNav.route
        ){
            composable(route = Screen.StartedGamesScreen.route){}
            composable(route = Screen.ModifyGameScreen.route){}
        }
        composable(route = Screen.ProfileScreen.route){ Profile() }

    }
}
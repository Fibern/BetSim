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
import com.example.betsim.presentation.create_feature.create_event.CreateEventScreen
import com.example.betsim.presentation.create_feature.create_offer.CreateOfferMainScreen
import com.example.betsim.presentation.main.MainViewModel
import com.example.betsim.presentation.modify.ModifyScreen
import com.example.betsim.presentation.profile.Profile
import com.example.betsim.presentation.offers.OffersScreen
import com.example.betsim.presentation.events.EventsScreen
import com.example.betsim.presentation.util.Screen

@Composable
fun ModNavHost(
    viewModel: MainViewModel,
    navController: NavHostController,
    mainNavController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CreateEventNav.route,
        modifier = Modifier.padding(paddingValues)
    ){

        navigation(
            startDestination = Screen.EventsScreen.route,
            route = Screen.CreateEventNav.route
        ){
            
            composable(
                route = Screen.EventsScreen.route,
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
            ){ EventsScreen(navController = navController) }

            composable(
                Screen.OffersScreen.route,
                arguments = listOf(
                    navArgument("mod"){
                        type = NavType.BoolType
                        defaultValue = true
                    },
                    navArgument("id"){
                        type = NavType.IntType
                    }
                )
            ) { OffersScreen(mainViewModel = viewModel , navController = navController) }

            composable(route = Screen.CreateEventScreen.route){ CreateEventScreen(navController) }
            composable(
                route = Screen.CreateOfferMainScreen.route,
                arguments = listOf(
                    navArgument("id"){
                        type = NavType.IntType
                    }
                )
            ){ CreateOfferMainScreen(navController) }
        }
        composable(
            Screen.StartedOffersScreen.route,
            arguments = listOf(
                navArgument("mod"){
                    type = NavType.BoolType
                    defaultValue = true
                },
                navArgument("id"){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){ OffersScreen(mainViewModel = viewModel, navController = navController) }
        composable(
            route = Screen.ModifyGameScreen.route,
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){ ModifyScreen(navController) }
        composable(route = Screen.ProfileScreen.route){ Profile(viewModel, mainNavController, navController) }

    }
}
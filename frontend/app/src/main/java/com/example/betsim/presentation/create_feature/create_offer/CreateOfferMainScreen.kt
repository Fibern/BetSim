package com.example.betsim.presentation.create_feature.create_offer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.util.Screen
import com.example.betsim.presentation.common.components.BetSimSubsidiaryTopBar

@Composable
fun CreateOfferMainScreen(
    mainNavController: NavHostController,
    viewModel: CreateOfferViewModel = hiltViewModel()
) {

    val navController = rememberNavController()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BetSimSubsidiaryTopBar(text = "Tworzenie rozgrywki") {
                if (navController.currentDestination?.route == Screen.AddGameScreen.route){
                    mainNavController.navigateUp()
                }else {
                    navController.navigateUp()
                }
            }
        }
    ) { paddingValues ->

        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Screen.AddGameScreen.route
        ){
            composable(Screen.AddGameScreen.route){ CreateOfferScreen(viewModel = viewModel, navController = navController) }
            composable(Screen.AddGameTeamsScreen.route){ CreateOfferTeamsScreen(viewModel = viewModel) }
        }

    }
}


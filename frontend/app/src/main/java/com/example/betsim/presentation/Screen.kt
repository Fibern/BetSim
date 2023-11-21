package com.example.betsim.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.auth.LoginScreen
import com.example.betsim.presentation.auth.RegisterScreen
import com.example.betsim.presentation.user_main.UserMainScreen

sealed class Screen(val route: String) {
    data object AuthNav: Screen("auth_navigation")
    data object LoginScreen: Screen("login_screen")
    data object RegisterScreen: Screen("register_screen")

    data object UserNav: Screen("user_navigation")
    data object UserMainScreen: Screen("user_main_screen")
    data object EventsScreen: Screen("events_screen")
    data object TodayEventsScreen: Screen("today_events_screen")
    data object EventDetailScreen: Screen("event_detail_screen")
    data object SettingsScreen: Screen("settings_screen")
    data object CouponsScreen: Screen("coupons_screen")
    data object LeaderboardScreen: Screen("leaderboard_screen")

}

@Composable
fun BetSimSurface() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.UserNav.route
        ) {
            navigation(
                startDestination = Screen.LoginScreen.route,
                route = Screen.AuthNav.route
            ) {
                composable(route = Screen.LoginScreen.route) {
                    LoginScreen(navController)
                }
                composable(route = Screen.RegisterScreen.route) {
                    RegisterScreen(navController)
                }
            }
            navigation(
                startDestination = Screen.UserMainScreen.route,
                route = Screen.UserNav.route
            ){
                composable(route = Screen.UserMainScreen.route){
                    UserMainScreen()
                }

            }

        }
    }
}
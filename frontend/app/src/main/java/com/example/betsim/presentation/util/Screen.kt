package com.example.betsim.presentation.util

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
import com.example.betsim.presentation.main.UserMainScreen

sealed class Screen(val route: String) {
    data object AuthNav: Screen("auth_navigation")
    data object LoginScreen: Screen("login_screen")
    data object RegisterScreen: Screen("register_screen")
    data object UserNav: Screen("user_navigation")
    data object UserMainScreen: Screen("user_main_screen")
    data object TournamentsNav: Screen("tournaments_navigation")
    data object TodayTournamentsNav: Screen("today_tournaments_navigation")
    data object TournamentsScreen: Screen("tournaments_screen?today={today}mod={mod}")
    data object TodayTournamentsScreen: Screen("today_tournaments_screen?today={today}mod={mod}")
    data object TournamentDetailScreen: Screen("tournament_detail_screen")
    data object TodayTournamentDetailScreen: Screen("today_tournament_detail_screen")
    data object ProfileScreen: Screen("settings_screen")
    data object CouponsNav: Screen("coupons_navigation")
    data object CouponsScreen: Screen("coupons_screen")
    data object CouponDetailsScreen: Screen("coupon_details_screen")
    data object LeaderboardScreen: Screen("leaderboard_screen")
    data object EventsNav: Screen("event_navigation")
    data object AddTournamentScreen: Screen("add_event_screen")
    data object AddGameMainScreen: Screen("add_game_main_screen")
    data object AddGameScreen: Screen("add_game_screen")
    data object AddGameTeamsScreen: Screen("add_game_teams_screen?")
    data object StartedGamesScreen: Screen("started_games_screen?today={today}mod={mod}")
    data object ModifyGameScreen: Screen("modify_game_screen")

}

@Composable
fun BetSimSurface() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.AuthNav.route
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
                    UserMainScreen(navController)
                }

            }

        }
    }
}
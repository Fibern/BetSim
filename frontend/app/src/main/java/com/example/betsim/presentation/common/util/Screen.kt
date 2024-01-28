package com.example.betsim.presentation.common.util

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
    data object MainScreen: Screen("main_screen")
    data object EventsNav: Screen("events_navigation")
    data object EventsScreen: Screen("events_screen?today={today}&mod={mod}")
    data object OffersScreenDefault: Screen("offers_screen")
    data object OffersScreen: Screen("offers_screen?mod={mod}&id={id}")
    data object TodayOffersScreen: Screen("today_offers_screen?mod={mod}&id={id}")
    data object ProfileScreen: Screen("settings_screen")
    data object CouponsNav: Screen("coupons_navigation")
    data object CouponsScreen: Screen("coupons_screen")
    data object CouponDetailsScreen: Screen("coupon_details_screen")
    data object LeaderboardScreen: Screen("leaderboard_screen")
    data object CreateEventNav: Screen("create_event_navigation")
    data object CreateEventScreen: Screen("create_event_screen")
    data object CreateOfferMainScreenDefault: Screen("create_offer_main_screen")
    data object CreateOfferMainScreen: Screen("create_offer_main_screen?id={id}")
    data object CreateOfferScreen: Screen("create_offer_screen")
    data object CreateOfferTeamsScreen: Screen("create_offer_teams_screen")
    data object StartedOffersScreen: Screen("started_offers_screen?mod={mod}&id={id}")
    data object ModifyGameScreenDefault: Screen("modify_game_screen")
    data object ModifyGameScreen: Screen("modify_game_screen?id={id}")

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
                startDestination = Screen.MainScreen.route,
                route = Screen.UserNav.route
            ){
                composable(route = Screen.MainScreen.route){
                    UserMainScreen(navController)
                }

            }

        }
    }
}
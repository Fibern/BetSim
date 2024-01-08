package com.example.betsim.presentation.main.components.nav_hosts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.betsim.presentation.util.Screen
import com.example.betsim.presentation.coupon_details.CouponDetailsScreen
import com.example.betsim.presentation.coupons.CouponsScreen
import com.example.betsim.presentation.leaderboard.LeaderboardScreen
import com.example.betsim.presentation.main.MainCouponState
import com.example.betsim.presentation.main.MainEvent
import com.example.betsim.presentation.main.MainViewModel
import com.example.betsim.presentation.main.components.coupon.CouponFog
import com.example.betsim.presentation.profile.Profile
import com.example.betsim.presentation.tournament_details.TournamentDetailScreen
import com.example.betsim.presentation.tournaments.TournamentsScreen

@Composable
fun UserNavHost(viewModel: MainViewModel, navController: NavHostController, paddingValues: PaddingValues) {
    val coupon: MainCouponState by remember{
        viewModel.couponState
    }

    NavHost(
        navController = navController,
        startDestination = Screen.TournamentsNav.route,
        modifier = Modifier.padding(paddingValues)
    ){

        navigation(startDestination = Screen.TodayTournamentsScreen.route, route = Screen.TodayTournamentsNav.route){
            composable(
                Screen.TodayTournamentsScreen.route,
                arguments = listOf(
                    navArgument("today"){
                        type = NavType.BoolType
                        defaultValue = true
                    },
                    navArgument("mod"){
                        type = NavType.BoolType
                        defaultValue = false
                    }
                )
            ) { TournamentsScreen(navController = navController) }
            composable(
                Screen.TodayTournamentDetailScreen.route,
                arguments = listOf(
                    navArgument("today"){
                        type = NavType.BoolType
                        defaultValue = true
                    },
                    navArgument("mod"){
                        type = NavType.BoolType
                        defaultValue = false
                    }
                )
            ) { TournamentDetailScreen(mainViewModel = viewModel, navController = navController) }
        }

        navigation(startDestination = Screen.TournamentsScreen.route, route = Screen.TournamentsNav.route){
            composable(
                Screen.TournamentsScreen.route,
                arguments = listOf(
                    navArgument("today"){
                        type = NavType.BoolType
                        defaultValue = false
                    },
                    navArgument("mod"){
                        type = NavType.BoolType
                        defaultValue = false
                    }
                )
            ) { TournamentsScreen(navController = navController) }
            composable(
                Screen.TournamentDetailScreen.route,
                arguments = listOf(
                    navArgument("today"){
                        type = NavType.BoolType
                        defaultValue = false
                    },
                    navArgument("mod"){
                        type = NavType.BoolType
                        defaultValue = false
                    }
                )
            ) { TournamentDetailScreen(mainViewModel = viewModel, navController = navController) }
        }

        navigation(startDestination = Screen.CouponsScreen.route, route = Screen.CouponsNav.route){
            composable(Screen.CouponsScreen.route) {
                CouponsScreen(viewModel, navController)
            }
            composable(Screen.CouponDetailsScreen.route) {
                CouponDetailsScreen(navController)
            }
        }

        composable(Screen.ProfileScreen.route) { Profile(viewModel, navController) }

        composable(Screen.LeaderboardScreen.route) { LeaderboardScreen() }
    }

    CouponFog(padding = paddingValues, active = !coupon.collapsed) {
        viewModel.onEvent(MainEvent.CollapsedChange(true))
    }

    BackHandler(enabled = true) {
        if (!coupon.collapsed){
            viewModel.onEvent(MainEvent.CollapsedChange(true))
            return@BackHandler
        }
        navController.navigateUp()
        viewModel.onEvent(MainEvent.HiddenChange(false))
    }

}
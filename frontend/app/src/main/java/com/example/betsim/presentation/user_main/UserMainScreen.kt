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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.Screen
import com.example.betsim.presentation.coupons.CouponsScreen
import com.example.betsim.presentation.event_details_user.EventDetailScreen
import com.example.betsim.presentation.events_user.EventsUserScreen
import com.example.betsim.presentation.leaderboard.LeaderboardScreen
import com.example.betsim.presentation.profile.Profile
import com.example.betsim.presentation.user_main.components.BetSimBottomAppBar
import com.example.betsim.presentation.user_main.components.BetSimTopAppBar
import com.example.betsim.presentation.user_main.components.FloatingABAnimation
import com.example.betsim.presentation.user_main.components.OpenedCouponFog


@Composable
fun UserMainScreen(
   // viewModel: UserMainViewModel = hiltViewModel()
){

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
            FloatingABAnimation(hidden = hidden, collapsed = collapsed) {
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
            startDestination = Screen.CouponsScreen.route,
            Modifier.padding(innerPadding)
        ){
            composable(Screen.EventsScreen.route) { EventsUserScreen() }
            composable(Screen.EventDetailScreen.route) { EventDetailScreen() }
            composable(Screen.CouponsScreen.route) { CouponsScreen() }
            composable(Screen.LeaderboardScreen.route) { LeaderboardScreen() }
            composable(Screen.TodayEventsScreen.route) { EventsUserScreen() }
            composable(Screen.SettingsScreen.route) { Profile() }
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
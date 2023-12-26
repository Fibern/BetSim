package com.example.betsim.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.main.components.BetSimBottomAppBar
import com.example.betsim.presentation.main.components.BetSimTopAppBar
import com.example.betsim.presentation.main.components.FloatingActionAnimation
import com.example.betsim.presentation.main.components.nav_hosts.ModNavHost
import com.example.betsim.presentation.main.components.nav_hosts.UserNavHost


@Composable
fun UserMainScreen(
    viewModel: MainViewModel = hiltViewModel()
){
    val navController = rememberNavController()
    val modEnabled by remember {
        viewModel.modEnabled
    }
    val coupon by remember {
        viewModel.couponState
    }
    val appBarsHidden by remember {
        viewModel.mainAppBarsHidden
    }

    Scaffold(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                viewModel.modEnabled.value = !modEnabled
                //TODO
                viewModel.onEvent(MainEvent.CollapsedChange(true))
            },

        topBar = {
                if (!appBarsHidden) {
                    BetSimTopAppBar(modEnabled)
                }
            },

        bottomBar = {
                if (!appBarsHidden) {
                    BetSimBottomAppBar(
                        modEnabled,
                        navController
                    ) {
                        viewModel.onEvent(MainEvent.HiddenChange(it))
                    }
                }
            },

        floatingActionButton = {
            if (modEnabled) {

            } else {
                FloatingActionAnimation(
                    coupon = coupon,
                    onClick = {
                        viewModel.onEvent(MainEvent.CollapsedChange(false))
                    },
                    onDeleteClick = { game ->
                        viewModel.onEvent(MainEvent.DeleteGame(game))
                    },
                    onValueChange = {
                        viewModel.onEvent(MainEvent.EnteredValue(it))
                    }
                )
            }
        }

    ) { innerPadding ->

        if (modEnabled){
            ModNavHost(
                viewModel = viewModel,
                navController = navController,
                paddingValues = innerPadding
            )
        }else{
            UserNavHost(
                viewModel = viewModel,
                navController = navController,
                paddingValues = innerPadding
            )
        }

    }

}

@Preview
@Composable
fun UserMainPreview(){
    UserMainScreen()
}
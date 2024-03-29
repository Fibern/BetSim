package com.example.betsim.presentation.main

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.common.components.BasicLoadingScreen
import com.example.betsim.presentation.common.components.SemiTransparentLoadingScreen
import com.example.betsim.presentation.common.util.Screen
import com.example.betsim.presentation.main.components.BetSimBottomAppBar
import com.example.betsim.presentation.main.components.BetSimTopAppBar
import com.example.betsim.presentation.main.components.FloatingActionAnimation
import com.example.betsim.presentation.main.components.nav_hosts.ModNavHost
import com.example.betsim.presentation.main.components.nav_hosts.UserNavHost
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun UserMainScreen(
    mainNavController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
){
    val navController = rememberNavController()
    val navStackEntry by navController.currentBackStackEntryAsState()
    val coupon by remember { viewModel.couponState }
    val appBarsHidden by remember { viewModel.mainAppBarsHidden }
    val isLoading by remember { viewModel.isLoading }
    val isLoadingSemiTransparent by remember { viewModel.isLoadingSemiTransparent }
    val user by remember { viewModel.user }
    val toast by remember { viewModel.toastMessage }
    val context = LocalContext.current

    if (toast.isNotBlank()) {
        LaunchedEffect(toast) {
            Toast.makeText(
                context,
                toast,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.clearToast()
        }
    }

    navController.addOnDestinationChangedListener{ _: NavController, destination: NavDestination, _: Bundle? ->
        viewModel.onEvent(MainEvent.DestinationChange(destination.route))
    }

    Scaffold(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                viewModel.onEvent(MainEvent.CollapsedChange(true))
            },

        topBar = {
                if (!appBarsHidden) {
                    BetSimTopAppBar(user){
                        viewModel.onEvent(MainEvent.Refresh)
                    }
                }
            },

        bottomBar = {
                if (!appBarsHidden) {
                    BetSimBottomAppBar(
                        user.isMod,
                        navController
                    ) {
                        viewModel.onEvent(MainEvent.HiddenChange(it))
                    }
                }
            },

        floatingActionButton = {
            if (user.isMod && (
                    navStackEntry?.destination?.route == Screen.EventsScreen.route)
                ) {
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = {
                        navController.navigate(Screen.CreateEventScreen.route)
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 500.dp, start = 200.dp)
                ) {
                    Icon(Icons.Filled.Add, "add", tint = MaterialTheme.colorScheme.onPrimary)
                }

            } else {
                FloatingActionAnimation(
                    coupon = coupon,
                    onClick = {
                        viewModel.onEvent(MainEvent.CollapsedChange(false))
                    },
                    onDeleteClick = { offer ->
                        viewModel.onEvent(MainEvent.DeleteGame(offer))
                    },
                    onValueChange = {
                        viewModel.onEvent(MainEvent.EnteredValue(it))
                    },
                    onBet = { viewModel.onEvent(MainEvent.MakeBet) }
                )
            }
        }

    ) { innerPadding ->


        if (isLoading)
            BasicLoadingScreen()
        else {
            if (user.isMod) {
                ModNavHost(
                    viewModel = viewModel,
                    mainNavController = mainNavController,
                    navController = navController,
                    paddingValues = innerPadding
                )
            } else {
                UserNavHost(
                    viewModel = viewModel,
                    mainNavController = mainNavController,
                    navController = navController,
                    paddingValues = innerPadding
                )
            }
        }
    }

    if (isLoadingSemiTransparent)
        SemiTransparentLoadingScreen()


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val notificationsPermissionState =
            rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)

        LaunchedEffect(key1 = Unit) {
            notificationsPermissionState.launchPermissionRequest()
        }
    }


}


@Preview
@Composable
fun UserMainPreview(){
    UserMainScreen(mainNavController = rememberNavController())
}
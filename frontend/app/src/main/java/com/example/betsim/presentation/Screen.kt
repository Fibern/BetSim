package com.example.betsim.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.auth.LoginScreen
import com.example.betsim.presentation.auth.RegisterScreen

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
}

@Composable
fun BetSimSurface() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.LoginScreen.route
        ) {
            composable(route = Screen.LoginScreen.route) {
                LoginScreen(navController)
            }
            composable(route = Screen.RegisterScreen.route) {
                RegisterScreen(navController)
            }
        }
    }
}
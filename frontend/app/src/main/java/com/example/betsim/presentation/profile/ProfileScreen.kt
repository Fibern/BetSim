package com.example.betsim.presentation.profile

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.betsim.presentation.common.components.SemiTransparentLoadingScreen
import com.example.betsim.presentation.main.MainViewModel
import com.example.betsim.presentation.util.Screen.AuthNav
import com.example.betsim.presentation.util.Screen.EventsScreen
import com.example.betsim.presentation.util.Screen.UserNav

@SuppressLint("RestrictedApi")
@Composable
fun Profile(
    mainViewModel: MainViewModel,
    mainNavController: NavController,
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val toast by remember { viewModel.toastMessage }
    val isLoading by remember { viewModel.isLoading }
    val success by remember { viewModel.success }
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

    LaunchedEffect(success) {
        if (success) {
            navController.popBackStack(route = EventsScreen.route, inclusive = true)
            mainNavController.navigate(AuthNav.route){
                popUpTo(route = UserNav.route){
                    inclusive = true
                }
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize(),
        ) {


            if (!mainViewModel.user.value.isMod) {


                OutlinedButton(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 16.dp),
                    onClick = {
                        viewModel.onEvent(ProfileEvent.DeleteClicked)
                    },
                    content = {
                        Text(text = "Usuń konto", modifier = Modifier.padding(vertical = 8.dp))
                    }
                )

            }

            OutlinedButton(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .padding(horizontal = 16.dp),
                onClick = {
                    viewModel.onEvent(ProfileEvent.LogoutClicked)
                },
                content = {
                    Text(text = "Wyloguj", modifier = Modifier.padding(vertical = 8.dp))
                }
            )
        }
    }

    if (isLoading)
        SemiTransparentLoadingScreen()

}
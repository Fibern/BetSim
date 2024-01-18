package com.example.betsim.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Surface
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
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.auth.components.AuthClickableText
import com.example.betsim.presentation.auth.components.AuthText
import com.example.betsim.presentation.auth.components.AuthTextField
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.SemiTransparentLoadingScreen
import com.example.betsim.presentation.util.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val login by remember { viewModel.login }
    val password by remember { viewModel.password }
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
            navController.navigate(Screen.UserNav.route){
                popUpTo(Screen.AuthNav.route){
                    inclusive = false
                }
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {

            AuthText(text = "Logowanie")
            Spacer(modifier = Modifier.height(10.dp))

            AuthTextField(
                text = login.value,
                label = "Login",
                onValChange = { viewModel.onEvent(AuthEvent.EnteredLogin(it)) },
                icon = Icons.Rounded.Person,
                isError = login.isError,
                errorText = login.errorText
            )

            AuthTextField(
                text = password.value,
                label = "Hasło",
                onValChange = { viewModel.onEvent(AuthEvent.EnteredPassword(it)) },
                icon = Icons.Rounded.Lock,
                isPassword = true,
                isError = password.isError,
                errorText = password.errorText
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                BetSimButton("Zaloguj"){
                    viewModel.onEvent(AuthEvent.OnAuthClick)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
            ) {
                AuthClickableText(text = "Nie masz konta?", coloredText = " Zarejestruj się tutaj!"){
                    navController.navigate(Screen.RegisterScreen.route)
                }
            }
        }
        if (isLoading) {
            SemiTransparentLoadingScreen()
        }
    }


}

@Preview
@Composable
fun LoginPreviewScreen(){

    LoginScreen(navController = rememberNavController())

}
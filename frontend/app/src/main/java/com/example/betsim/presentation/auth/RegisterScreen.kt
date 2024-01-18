package com.example.betsim.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.auth.components.AuthText
import com.example.betsim.presentation.auth.components.AuthTextField
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.SemiTransparentLoadingScreen
import com.example.betsim.presentation.util.Screen

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterScreenViewModel = hiltViewModel()
){

    val login by remember { viewModel.login }
    val email by remember { viewModel.email }
    val password by remember { viewModel.password }
    val password2 by remember { viewModel.password2 }
    val toast by remember { viewModel.toastMessage }
    val success by remember { viewModel.success }
    val isLoading by remember { viewModel.isLoading }
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
        if (success) navController.navigateUp()
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

            AuthText(text = "Rejestracja")
            Spacer(modifier = Modifier.height(10.dp))

            AuthTextField(
                text = login.value,
                label = "Nazwa użytkownika",
                onValChange = { viewModel.onEvent(AuthEvent.EnteredLogin(it)) },
                icon = Icons.Rounded.Person,
                isError = login.isError,
                errorText = login.errorText
            )

            AuthTextField(
                text = email.value,
                label = "Email",
                onValChange = { viewModel.onEvent(AuthEvent.EnteredEmail(it)) },
                icon = Icons.Rounded.Email,
                isError = email.isError,
                errorText = email.errorText,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
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

            AuthTextField(
                text = password2.value,
                label = "Powtórz hasło",
                onValChange = { viewModel.onEvent(AuthEvent.EnteredPassword2(it)) },
                icon = Icons.Rounded.Lock,
                isPassword = true,
                isError = password2.isError,
                errorText = password2.errorText
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                BetSimButton(text = "Zarejestruj") {
                    viewModel.onEvent(AuthEvent.OnAuthClick)
                }

            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
            ){
                Text(
                    text = buildAnnotatedString {
                        append("Masz już konto? ")
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            append("Zaloguj się tutaj!")
                        }
                    },
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.LoginScreen.route)
                    }
                )
            }

        }
        if (isLoading){
            SemiTransparentLoadingScreen()
        }
    }
}

@Preview
@Composable
fun RegisterPreview(){
    RegisterScreen(navController = rememberNavController())
}
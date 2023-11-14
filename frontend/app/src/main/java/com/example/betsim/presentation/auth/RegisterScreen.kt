package com.example.betsim.presentation.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.betsim.presentation.Screen
import com.example.betsim.presentation.auth.components.AuthInput

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterScreenViewModel = hiltViewModel()
){

    val loginState = viewModel.login.value
    val emailState = viewModel.email.value
    val passwordState = viewModel.password.value
    val password2State = viewModel.password2.value


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

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Rejestracja",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            AuthInput(
                text = loginState,
                label = "Login",
                onValChange = { viewModel.onEvent(AuthEvent.EnteredLogin(it)) },
                icon = Icons.Rounded.Person
            )
            Spacer(modifier = Modifier.height(10.dp))
            AuthInput(
                text = emailState,
                label = "Email",
                onValChange = { viewModel.onEvent(AuthEvent.EnteredEmail(it)) },
                icon = Icons.Rounded.Email
            )
            Spacer(modifier = Modifier.height(10.dp))
            AuthInput(
                text = passwordState,
                label = "Hasło",
                onValChange = { viewModel.onEvent(AuthEvent.EnteredPassword(it)) },
                icon = Icons.Rounded.Lock,
                isPassword = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            AuthInput(
                text = password2State,
                label = "Powtórz hasło",
                onValChange = { viewModel.onEvent(AuthEvent.EnteredPassword2(it)) },
                icon = Icons.Rounded.Lock,
                isPassword = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 100.dp)
                ) {
                    Text(text = "Zarejestruj")
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
                                color = Color.Blue
                            )
                        ) {
                            append("Zaloguj się tutaj")
                        }
                    },
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.LoginScreen.route)
                    }
                )
            }
        }
    }

}
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.betsim.presentation.Screen
import com.example.betsim.presentation.auth.components.AuthInput
import com.example.betsim.presentation.auth.components.AuthTextField

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val loginState = viewModel.login.value
    val passwordState = viewModel.password.value

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

            AuthTextField(text = "Logowanie")
            Spacer(modifier = Modifier.height(10.dp))

            AuthInput(
                text = loginState,
                label = "Login",
                onValChange = { viewModel.onEvent(AuthEvent.EnteredLogin(it)) },
                icon = Icons.Rounded.Person
            )
            Spacer(modifier = Modifier.height(10.dp))

            AuthInput(
                text = passwordState,
                label = "Hasło",
                onValChange = { viewModel.onEvent(AuthEvent.EnteredPassword(it)) },
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
                    onClick = {
                              navController.navigate(Screen.UserNav.route){
                                  popUpTo(Screen.AuthNav.route){
                                      inclusive = true
                                  }
                              }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = "Zaloguj")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Nie masz konta? ")
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            append("Zarejestruj się tutaj")
                        }
                    },
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.RegisterScreen.route)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginPreviewScreen(){

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

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
                        text = "Logowanie",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 36.dp),
                        onValueChange = {},
                        singleLine = true
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 36.dp),
                        visualTransformation =  PasswordVisualTransformation(),
                        onValueChange = {},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
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
                        Text(text = "Zaloguj")
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
                            append("Nie masz konta? ")
                            withStyle(
                                SpanStyle(
                                    color = Color.Blue
                                )
                            ) {
                                append("Zarejestruj się tutaj")
                            }
                        },
                    )
                }
            }
        }
    }}
package com.example.betsim.presentation.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel()
){
    val loginState = viewModel.login.value
    val passwordState = viewModel.password.value

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = loginState,
                onValueChange = {
                    viewModel.onEvent(AuthEvent.EnteredLogin(it))
                }
            )

            OutlinedTextField(
                value = passwordState,
                onValueChange = {
                    viewModel.onEvent(AuthEvent.EnteredPassword(it))
                }
            )

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            OutlinedTextField(value = "aaa", onValueChange = {})
            OutlinedTextField(value = "aaa", onValueChange = {})

        }
    }
}
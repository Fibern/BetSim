package com.example.betsim.presentation.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.betsim.presentation.main.MainViewModel

@Composable
fun Profile(
    mainViewModel: MainViewModel,
    navController: NavHostController
){
    
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OutlinedButton(
            modifier = Modifier
                .wrapContentHeight()
                .padding(16.dp),
            onClick = {
                navController.popBackStack(0, true)
                mainViewModel.modEnabled.value = !mainViewModel.modEnabled.value
            },
            content = {
                Text(text = "Zmiana", modifier = Modifier.padding(vertical = 8.dp))
            }
        ) 
    }
    
}
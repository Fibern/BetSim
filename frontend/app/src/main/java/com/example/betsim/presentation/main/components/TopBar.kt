package com.example.betsim.presentation.main.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.betsim.R.drawable.ic_casino_chip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetSimTopAppBar(
    modEnabled: Boolean
){

    TopAppBar(
        title = { Text("username") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            if (!modEnabled){
                Text(text = "2500", color = MaterialTheme.colorScheme.onPrimary)
                Icon(
                    painterResource(id = ic_casino_chip),
                    "",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    )
}
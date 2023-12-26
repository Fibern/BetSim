package com.example.betsim.presentation.main.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatAB(onClick: () -> Unit, count: Int){
    FloatingActionButton(
        onClick = {
            onClick()
        },
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 500.dp, start = 200.dp)
    ){
        BadgedBox(
            badge = {
                Badge {
                    Text(text = count.toString())
                }
            }
        ) {
            Icon(Icons.Filled.ReceiptLong, "kupon", tint = MaterialTheme.colorScheme.onPrimary )
        }

    }
}

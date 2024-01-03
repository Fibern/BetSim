package com.example.betsim.presentation.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LeaderboardScreen(

) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            contentPadding = PaddingValues(horizontal =  16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            
            items(20){
                LeaderboardItem(place = it)
            }

        }

    }

}


@Composable
fun LeaderboardItem(place: Int){
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary)
          //  .height(68.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        ListItem(
            leadingContent = { Text(text = place.toString(), color = MaterialTheme.colorScheme.onSecondary) },
            headlineContent = { Text(text = "Username", color = MaterialTheme.colorScheme.onSecondary)},
            trailingContent = { Text(text = "650", color = MaterialTheme.colorScheme.onSecondary) },
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.secondary,
                headlineColor = MaterialTheme.colorScheme.onSecondary,
                leadingIconColor = MaterialTheme.colorScheme.onSecondary,
                trailingIconColor = MaterialTheme.colorScheme.onSecondary
            )
        )

    }
}
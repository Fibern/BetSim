package com.example.betsim.presentation.user_main.components

import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.betsim.domain.model.TournamentGame

@Composable
fun FloatingCouponItem(game: TournamentGame){
    val odd = game.odds[game.selected.value!!]



    ListItem(
        headlineContent = { Text(odd.name, color = MaterialTheme.colorScheme.onPrimary) },
        supportingContent = { Text("${game.homeTeam} - ${game.awayTeam}", color = MaterialTheme.colorScheme.onPrimary) },
        trailingContent = { Text(odd.odd.toString(), color = MaterialTheme.colorScheme.onPrimary) },
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.primary)
    )

}
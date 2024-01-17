package com.example.betsim.presentation.tournaments.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.model.EventIcons
import com.example.betsim.domain.model.Tournament

@Composable
fun TournamentItem(
    tournament: Tournament,
    isMod: Boolean,
    modifier: Modifier,
    onDelete: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .height(150.dp)
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(8.dp), clip = true)
            .background(colorScheme.secondary)
        ,
        contentAlignment = Alignment.Center
    ) {
        if (isMod) {
            IconButton(
                onClick = onDelete,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(Icons.Filled.DeleteForever, "", tint = colorScheme.onSecondary)
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                tournament.icon.icon,
                tournament.icon.name,
                tint = colorScheme.onSecondary,
                modifier = Modifier.size(48.dp)
            )
            Text(text = tournament.name, color = colorScheme.onSecondary)
        }
    }
}

@Preview
@Composable
fun TournamentItemPreview(){
    TournamentItem(tournament = Tournament("tournament", EventIcons.Football),false, modifier = Modifier){

    }
}
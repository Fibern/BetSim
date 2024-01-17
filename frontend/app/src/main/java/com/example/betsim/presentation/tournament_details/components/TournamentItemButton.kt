package com.example.betsim.presentation.tournament_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TournamentItemButton(
    isMod: Boolean,
    onClick: () -> Unit,
    name: String,
    odd: String,
    selected: Boolean
){
    Button(
        onClick = {
            if (!isMod) {
                onClick()
            }
        },
        modifier = Modifier
            .width(90.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color.Black else MaterialTheme.colorScheme.primary
        )
    ) {
        Column {
            Text(
                text = name,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = odd,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
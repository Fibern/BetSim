package com.example.betsim.presentation.events_user.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun EventItem(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .height(150.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}
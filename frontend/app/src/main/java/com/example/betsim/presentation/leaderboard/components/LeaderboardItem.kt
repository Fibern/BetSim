package com.example.betsim.presentation.leaderboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.betsim.R.drawable.ic_casino_chip
import com.example.betsim.data.remote.responses.TopUser

@Composable
fun LeaderboardItem(
    user: TopUser,
    isUser: Boolean
){

    Row(
        modifier = Modifier
            .background(colorScheme.secondary)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        ListItem(
            leadingContent = { Text(text = user.place.toString(), color = colorScheme.onSecondary) },
            headlineContent = { Text(text = user.userName, color = colorScheme.onSecondary) },
            trailingContent = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = user.points.toString(),
                        color = colorScheme.onSecondary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painterResource(id = ic_casino_chip),
                        "",
                        tint = colorScheme.onSecondary)
                }
            },
            colors = ListItemDefaults.colors(
                containerColor = if(isUser) colorScheme.tertiary
                                 else colorScheme.secondary,
                headlineColor = colorScheme.onSecondary,
                leadingIconColor = colorScheme.onSecondary,
                trailingIconColor = colorScheme.onSecondary
            )
        )

    }
}
package com.example.betsim.presentation.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betsim.presentation.leaderboard.components.LeaderboardItem

@Composable
fun LeaderboardScreen(

) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {

            items(20) {
                LeaderboardItem(place = it)
            }

        }

    }

}

@Preview
@Composable
fun LeaderboardPreview(){
    LeaderboardScreen()
}
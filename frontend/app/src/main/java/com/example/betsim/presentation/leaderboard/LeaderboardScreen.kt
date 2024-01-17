package com.example.betsim.presentation.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 8.dp),
        ) {

            LazyColumn(
                modifier = Modifier
                    .height(0.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {

                items(20) {
                    LeaderboardItem(place = it + 1, false)
                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                LeaderboardItem(place = 24, true)
            }

        }
    }

}

@Preview
@Composable
fun LeaderboardPreview(){
    LeaderboardScreen()
}
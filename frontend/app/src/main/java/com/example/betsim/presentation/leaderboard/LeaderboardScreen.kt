package com.example.betsim.presentation.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.betsim.presentation.common.components.SemiTransparentLoadingScreen
import com.example.betsim.presentation.leaderboard.components.LeaderboardItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LeaderboardScreen(
    viewModel: LeaderBoardViewModel = hiltViewModel()
) {

    val users by remember { viewModel.rankingUsers }
    val isLoading by remember { viewModel.isLoading }
    val pull = rememberPullRefreshState(refreshing = isLoading, onRefresh = {
        viewModel.onEvent(
            LeaderboardEvent.Refresh
        )
    } )

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pull)
                .padding(vertical = 16.dp, horizontal = 8.dp),
        ) {

            Box(
                modifier = Modifier
                    .height(0.dp)
                    .weight(1f),
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    modifier = Modifier.fillMaxSize()
                ) {

                    if (users != null) {
                        items(users!!.topUsers) {
                            LeaderboardItem(user = it, isUser = it.place == users!!.user.place)
                        }
                    }
                }
                PullRefreshIndicator(
                    refreshing = false,
                    state = pull,
                    modifier = Modifier.align(Alignment.TopCenter),
                )
            }

            Column(
                modifier = Modifier
                    .padding(top = 24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (users != null)
                    LeaderboardItem(users!!.user, true)
            }

        }
    }

    if (isLoading)
        SemiTransparentLoadingScreen()

}

@Preview
@Composable
fun LeaderboardPreview(){
    LeaderboardScreen()
}
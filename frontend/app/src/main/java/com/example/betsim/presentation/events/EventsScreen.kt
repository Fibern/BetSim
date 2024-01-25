package com.example.betsim.presentation.events

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.common.components.BlankScreenItem
import com.example.betsim.presentation.common.components.SemiTransparentLoadingScreen
import com.example.betsim.presentation.events.components.EventItem
import com.example.betsim.presentation.util.Screen


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventsScreen(
    viewModel: EventsScreenViewModel = hiltViewModel(),
    navController: NavHostController
){

    val events = viewModel.events
    val isMod by remember { viewModel.isMod }
    val isLoading by remember { viewModel.isLoading }
    val pull = rememberPullRefreshState(refreshing = isLoading, onRefresh = {
        viewModel.onEvent(
            EventsScreenEvent.Refresh
        )
    } )


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pull)
        ) {

            if (events.isEmpty())
                BlankScreenItem(text = "Brak wydarzeń")

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
            ) {

                items(events) { event ->
                    EventItem(
                        event,
                        isMod,
                        Modifier.clickable(onClick = {
                            navController.navigate("${Screen.OffersScreenDefault.route}?id=${event.id}")
                        })
                    ) {
                        viewModel.onEvent(EventsScreenEvent.DeleteClicked(event.id))
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = false,
                state = pull,
                modifier = Modifier.align(Alignment.TopCenter),
            )

        }

    }

    if (isLoading){
        SemiTransparentLoadingScreen()
    }

}


@Composable
@Preview
fun EventsScreenPreview() {
    val fakeNavController = rememberNavController()

    EventsScreen(
        viewModel = hiltViewModel(),
        navController = fakeNavController
    )
}
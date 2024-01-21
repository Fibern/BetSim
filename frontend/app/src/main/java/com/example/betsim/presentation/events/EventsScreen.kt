package com.example.betsim.presentation.events

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.common.components.SemiTransparentLoadingScreen
import com.example.betsim.presentation.events.components.EventItem


@Composable
fun EventsScreen(
    viewModel: EventsScreenViewModel = hiltViewModel(),
    navController: NavHostController
){

    val events = viewModel.events
    val route by remember { viewModel.route }
    val isMod by remember { viewModel.isMod }
    val isLoading by remember { viewModel.isLoading }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ){
            items(events){event ->
                    EventItem(
                        event,
                        isMod,
                        Modifier.clickable(onClick = {
                            navController.navigate("$route?id=${event.id}")
                        })
                    ){
                        viewModel.onEvent(EventsScreenEvent.DeleteClicked(event.id))
                    }
            }
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
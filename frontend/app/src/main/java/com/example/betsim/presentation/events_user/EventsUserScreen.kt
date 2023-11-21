package com.example.betsim.presentation.events_user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betsim.presentation.events_user.components.EventItem


data class Event(
    val name: String
)

@Composable
fun EventsUserScreen(

){

    val events = listOf(Event("aaaa"),Event("123"),Event("aaaa"),Event("aaaa"),Event("aaaa"),Event("aaaa"),Event("aaaa"),Event("asaaa"))

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(events){event ->
                    EventItem(text = event.name)
            }
        }

    }

}
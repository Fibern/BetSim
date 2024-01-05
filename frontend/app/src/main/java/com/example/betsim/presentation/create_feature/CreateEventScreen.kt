package com.example.betsim.presentation.create_feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.BetSimSubsidiaryTopBar
import com.example.betsim.presentation.common.data.EventIcons
import com.example.betsim.presentation.create_feature.components.CreationTextField
import com.example.betsim.presentation.create_feature.components.IconDropdown

@Composable
fun CreateEventScreen(
    viewModel: CreateEventViewModel = hiltViewModel()
) {

    val eventName by remember { viewModel.eventName }

    val options = EventIcons.entries.toTypedArray()
    var selectedValue: EventIcons? by remember { mutableStateOf(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BetSimSubsidiaryTopBar(
                "Tworzenie wydarzenia"
            ) {

            }
        }
    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CreationTextField(
                    value = eventName,
                    onValueChange = { viewModel.onEvent(CreationEvent.EnteredName(it)) }
                )

                IconDropdown(
                    value = selectedValue,
                    options = options
                ) {
                    selectedValue = it
                }

                BetSimButton(text = "Utwórz") {

                }

            }

        }
    }
}


@Preview
@Composable
fun CreateEventScreenPreview(){
    CreateEventScreen()
}

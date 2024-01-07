package com.example.betsim.presentation.create_feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.BetSimSubsidiaryTopBar
import com.example.betsim.presentation.common.components.FormText
import com.example.betsim.presentation.common.data.EventIcons
import com.example.betsim.presentation.create_feature.components.CreationTextField
import com.example.betsim.presentation.create_feature.components.IconDropdown

@Composable
fun CreateEventScreen(
    navController: NavHostController,
    viewModel: CreateEventViewModel = hiltViewModel()
) {

    val event by remember { viewModel.eventState }
    val options = EventIcons.entries.toTypedArray()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BetSimSubsidiaryTopBar(
                "Tworzenie wydarzenia"
            ) {
                navController.navigateUp()
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                FormText(text = "Nazwa wydarzenia")
                CreationTextField(
                    value = event.name,
                    onValueChange = { viewModel.onEvent(CreationEvent.EnteredName(it)) },
                    hint = { Text(text = "Nazwa wydarzenia") }
                )
                Spacer(modifier = Modifier.height(16.dp))

                FormText(text = "Wybierz ikonę")
                IconDropdown(
                    value = event.icon,
                    options = options,
                    hint = {
                        Text(text = "Wybierz ikonę")
                    },
                    onClick = {
                        viewModel.onEvent(CreationEvent.SelectDropdown(icon = it))
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                BetSimButton(text = "Utwórz") {
                    viewModel.onEvent(CreationEvent.CreateClick)
                }

            }

        }
    }
}


@Preview
@Composable
fun CreateEventScreenPreview(){
    CreateEventScreen(rememberNavController())
}

package com.example.betsim.presentation.create_feature.create_event

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShortText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.BetSimSubsidiaryTopBar
import com.example.betsim.presentation.common.components.FormText
import com.example.betsim.domain.model.EventIcons
import com.example.betsim.presentation.common.components.SemiTransparentLoadingScreen
import com.example.betsim.presentation.create_feature.CreationEvent
import com.example.betsim.presentation.create_feature.components.CreationTextField
import com.example.betsim.presentation.create_feature.components.IconDropdown
import com.example.betsim.presentation.util.Screen

@Composable
fun CreateEventScreen(
    navController: NavHostController,
    viewModel: CreateEventViewModel = hiltViewModel()
) {

    val icon by remember { viewModel.icon }
    val name by remember { viewModel.name }
    val options = EventIcons.entries.toTypedArray()
    val isLoading by remember { viewModel.isLoading }
    val success by remember { viewModel.success }
    val toast by remember { viewModel.toastMessage }
    val context = LocalContext.current

    if (toast.isNotBlank()) {
        LaunchedEffect(toast) {
            Toast.makeText(
                context,
                toast,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.clearToast()
        }
    }
    LaunchedEffect(success) {
        if (success) navController.navigate(Screen.TournamentsScreen.route)
    }

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
                    leadingIcon = Icons.Rounded.ShortText,
                    value = name.value,
                    onValueChange = { viewModel.onEvent(CreationEvent.EnteredName(it)) },
                    hint = "Nazwa wydarzenia",
                    isError = name.isError,
                    errorMessage = name.errorText
                )

                FormText(text = "Wybierz ikonę")
                IconDropdown(
                    value = icon.value,
                    options = options,
                    hint = {
                        Text(text = "Wybierz ikonę")
                    },
                    onClick = {
                        viewModel.onEvent(CreationEvent.SelectDropdown(icon = it))
                    },
                    isError = icon.isError
                )
                Text(
                    text = icon.errorText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .padding(horizontal = 56.dp),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(4.dp))

                BetSimButton(text = "Utwórz") {
                    viewModel.onEvent(CreationEvent.CreateClick)
                }

            }

        }

        if (isLoading) {
            SemiTransparentLoadingScreen()
        }
    }
}


@Preview
@Composable
fun CreateEventScreenPreview(){
    CreateEventScreen(rememberNavController())
}

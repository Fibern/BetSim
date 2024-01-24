package com.example.betsim.presentation.modify

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.betsim.data.model.OfferType
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.BetSimSubsidiaryTopBar
import com.example.betsim.presentation.common.components.SemiTransparentLoadingScreen
import com.example.betsim.presentation.modify.components.ModifyMatchType
import com.example.betsim.presentation.modify.components.ModifySelectionType
import com.example.betsim.presentation.util.Screen
import java.time.format.DateTimeFormatter

@Composable
fun ModifyScreen(
    navController: NavHostController,
    viewModel: ModifyViewModel = hiltViewModel()
){

    val offer by remember{ viewModel.offer }
    val home by remember { viewModel.home }
    val away by remember { viewModel.away }
    val selected by remember { viewModel.selected }
    val isLoading by remember { viewModel.isLoading }
    val success by remember { viewModel.success }
    val eventId by remember { viewModel.eventId }
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
        if (success)
            navController.navigate("${Screen.OffersScreenDefault.route}?id=$eventId"){
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BetSimSubsidiaryTopBar(text = "Wprowadzanie wyniku") {
                navController.navigateUp()
            }
        }
    ) { paddingValues ->

        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        DateTimeFormatter.ofPattern("yyyy.MM.dd").format(offer.dateTime),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        DateTimeFormatter.ofPattern("HH:mm").format(offer.dateTime),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = offer.title,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }


                Spacer(modifier = Modifier.height(24.dp))

                Row {
                    when (OfferType.entries[offer.type]) {
                        OfferType.Match -> {
                            ModifyMatchType(
                                options = offer.odds,
                                home = home,
                                away = away
                            )
                            { home, score ->
                                viewModel.onEvent(ModifyEvent.ScoreEntered(home, score))
                            }
                        }

                        OfferType.Selection -> {
                            ModifySelectionType(
                                options = offer.odds,
                                selected = selected
                            ) {
                                viewModel.onEvent(ModifyEvent.OptionChanged(it))
                            }
                        }
                    }
                }
                Row {
                    BetSimButton(text = "Zatwierdź") {
                        viewModel.onEvent(ModifyEvent.ConfirmClick)
                    }
                }
            }

            if (isLoading) {
                SemiTransparentLoadingScreen()
            }
        }
    }
}

@Preview
@Composable
fun ModifyScreenPreview(){
    ModifyScreen(rememberNavController(), hiltViewModel())
}
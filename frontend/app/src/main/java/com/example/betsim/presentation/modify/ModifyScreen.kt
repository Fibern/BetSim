package com.example.betsim.presentation.modify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.OfferType
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.BetSimSubsidiaryTopBar
import com.example.betsim.presentation.modify.components.ModifyMatchType
import com.example.betsim.presentation.modify.components.ModifyWinnerType

@Composable
fun ModifyScreen(
    navController: NavHostController,
    viewModel: ModifyViewModel = hiltViewModel()
){

    val state by remember{ viewModel.state }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BetSimSubsidiaryTopBar(text = "Wprowadzanie wyniku") {
                navController.navigateUp()
            }
        }
    ) {paddingValues ->

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

                when(state.type){
                    OfferType.Match -> {
                        ModifyMatchType(options = state.odds)
                    }
                    OfferType.Winner -> {
                        ModifyWinnerType(options = state.odds)
                    }
                }

                BetSimButton(text = "Zatwierdź") {
                    
                }

            }

        }

    }

}

@Preview
@Composable
fun ModifyScreenPreview(){
    val viewModel = ModifyViewModel()
    viewModel.state.value.odds.removeFirst()
    viewModel.state.value.odds.removeFirst()
    viewModel.state.value.odds.add(Odd(0, "team 1", "0.0"))
    viewModel.state.value.odds.add(Odd(1, "team 2", "0.0"))
    ModifyScreen(rememberNavController(), viewModel)
}

@Preview
@Composable
fun ModifyScreenPreview2(){
    val viewModel = ModifyViewModel()
    viewModel.state.value = viewModel.state.value.copy(
        type = OfferType.Winner
    )
    ModifyScreen(rememberNavController(), viewModel)
}
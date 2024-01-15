package com.example.betsim.presentation.modify

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.betsim.domain.model.OfferType
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.BetSimSubsidiaryTopBar
import com.example.betsim.presentation.modify.components.ModifyMatchType
import com.example.betsim.presentation.modify.components.ModifySelectionType
import java.time.format.DateTimeFormatter

@Composable
fun ModifyScreen(
    navController: NavHostController,
    viewModel: ModifyViewModel = hiltViewModel()
){

    val game by remember{ viewModel.game }
    val home by remember { viewModel.home }
    val away by remember { viewModel.away }
    val selected by remember { viewModel.selected }

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

                 Row(
                     modifier = Modifier
                         .fillMaxWidth(),
                     horizontalArrangement = Arrangement.SpaceBetween
                 ){
                     Text(
                         DateTimeFormatter.ofPattern("yyyy.MM.dd").format(game.date),
                         color = MaterialTheme.colorScheme.primary
                     )
                     Text(
                         DateTimeFormatter.ofPattern("HH:mm").format(game.date),
                         color = MaterialTheme.colorScheme.primary
                     )
                 }

                 Row(
                     modifier = Modifier
                         .fillMaxWidth(),
                     horizontalArrangement = Arrangement.Center
                 ) {
                     Text(
                         text = game.name,
                         color = MaterialTheme.colorScheme.primary,
                         style = MaterialTheme.typography.headlineSmall
                     )
                 }


                Spacer(modifier = Modifier.height(40.dp))

                when(game.type){
                    OfferType.Match -> {
                        ModifyMatchType(
                            options = game.odds,
                            home = home,
                            away = away
                        )
                        { home, score ->
                            viewModel.onEvent(ModifyEvent.ScoreEntered(home, score))
                        }
                    }
                    OfferType.Selection -> {
                        ModifySelectionType(
                            options = game.odds,
                            selected = selected
                        ){
                            viewModel.onEvent(ModifyEvent.OptionChanged(it))
                        }
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
    ModifyScreen(rememberNavController(), ModifyViewModel())
}
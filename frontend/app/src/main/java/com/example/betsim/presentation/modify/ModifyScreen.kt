package com.example.betsim.presentation.modify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.BetSimOutlinedTextField
import com.example.betsim.presentation.common.components.BetSimSubsidiaryTopBar

@Composable
fun ModifyScreen(
    navController: NavHostController
){

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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    Text(text = "team 1")
                    BetSimOutlinedTextField(value = "12", onValueChange = {})

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ){

                    Text(text = "team 1")
                    BetSimOutlinedTextField(value = "12", onValueChange = {})

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
    ModifyScreen(rememberNavController())
}

package com.example.betsim.presentation.create_feature

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.BetSimSubsidiaryTopBar
import com.example.betsim.presentation.create_feature.components.CreationTextField
import com.example.betsim.presentation.create_feature.components.Dropdown
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun CreateGameScreen(

) {




    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BetSimSubsidiaryTopBar(text = "Tworzenie rozgrywki") {
                
            }
        }
    ) { paddingValues ->

        val options = listOf("option1", "option2", "option3")
        var selected by remember { mutableStateOf(options[0]) }

        var selectedDate by remember { mutableStateOf(LocalDate.now()) }
        val datePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _, year, month, dayOfMonth ->
                selectedDate = LocalDate.of(year, month, dayOfMonth)
            },
            selectedDate.year,
            selectedDate.monthValue,
            selectedDate.dayOfMonth,
        )

        var selectedTime by remember { mutableStateOf(LocalTime.now()) }
        val timePickerDialog = TimePickerDialog(
            LocalContext.current,
            { _, hourOfDay, minute ->
                selectedTime = LocalTime.of(hourOfDay, minute)
            },
            selectedTime.hour,
            selectedTime.minute,
            true
        )



        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Column (
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Box {
                    CreationTextField(value = "")
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .alpha(0f)
                            .clickable(onClick = { datePickerDialog.show() }),
                    )
                }


                Box {
                    CreationTextField(value = "")
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .alpha(0f)
                            .clickable(onClick = {
                                timePickerDialog.show()
                            }),
                    )
                }

                Dropdown(
                    value = selected,
                    options = options
                ) { selected = it }


                CreationTextField(value = "")
                CreationTextField(value = "")

                BetSimButton(text = "Utwórz") {
                    
                }

            }
        }
    }
}


@Preview
@Composable
fun CreateGameScreenPreview(){
    CreateGameScreen()
}
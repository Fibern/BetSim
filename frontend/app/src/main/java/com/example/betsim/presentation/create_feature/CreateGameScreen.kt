package com.example.betsim.presentation.create_feature

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.betsim.domain.util.OfferType
import com.example.betsim.presentation.util.Screen
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.FormText
import com.example.betsim.presentation.create_feature.components.CreationTextField
import com.example.betsim.presentation.create_feature.components.Dropdown
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun CreateGameScreen(
    viewModel: CreateGameViewModel,
    navController: NavController,
) {


    val state by remember { viewModel.state }
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            viewModel.onEvent(CreationEvent.EnteredDate(LocalDate.of(year, month + 1, dayOfMonth)))
        },
        state.date?.year ?: LocalDate.now().year,
        if (state.date == null) LocalDate.now().monthValue
        else state.date!!.monthValue - 1,
        state.date?.dayOfMonth ?: LocalDate.now().dayOfMonth,
    )

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, hourOfDay, minute ->
            viewModel.onEvent(CreationEvent.EnteredTime(LocalTime.of(hourOfDay, minute)))
        },
        state.time?.hour ?: LocalTime.now().hour,
        state.time?.minute ?: LocalTime.now().minute,
        true
    )



    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            FormText(text = "Wybierz datę")
            Box {
                CreationTextField(
                    value = if (state.date == null) ""
                    else DateTimeFormatter.ofPattern("dd.MM.yyyy").format(state.date),
                    hint = { Text(text = "Wybierz datę") }
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .alpha(0f)
                        .clickable(onClick = {
                            datePickerDialog.show()
                        }),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            FormText(text = "Wybierz godzinę")
            Box {
                CreationTextField(
                    value = if (state.time == null) ""
                    else DateTimeFormatter.ofPattern("HH:mm").format(state.time),
                    hint = { Text(text = "Wybierz godzinę") }
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .alpha(0f)
                        .clickable(onClick = {
                            timePickerDialog.show()
                        }),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            FormText(text = "Rodzaj zakładu")
            Dropdown(
                value = state.type,
                options = OfferType.entries
            ) {
                viewModel.onEvent(CreationEvent.SelectDropdown(offerType = it))
            }

            Spacer(modifier = Modifier.height(24.dp))

            BetSimButton(text = "Przejdź dalej") {
                navController.navigate(Screen.AddGameTeamsScreen.route)
            }

        }
    }

}


@Preview
@Composable
fun CreateGameScreenPreview(){
    CreateGameScreen(hiltViewModel(), rememberNavController())
}
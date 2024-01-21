package com.example.betsim.presentation.create_feature.create_offer

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material.icons.rounded.Handshake
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.ShortText
import androidx.compose.material3.Icon
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
import com.example.betsim.data.model.OfferType
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.common.components.BetSimDropdown
import com.example.betsim.presentation.common.components.FormText
import com.example.betsim.presentation.create_feature.CreationEvent
import com.example.betsim.presentation.create_feature.components.CreationTextField
import com.example.betsim.presentation.util.Screen
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun CreateOfferScreen(
    viewModel: CreateOfferViewModel,
    navController: NavController,
) {

    val dateTextField by remember { viewModel.dateTextField }
    val timeTextField by remember { viewModel.timeTextField }
    val type by remember { viewModel.type }
    val offerName by remember { viewModel.offerName }
    val actionPossible by remember { viewModel.actionPossible }
    val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    fun toDate(date: LocalDate?): String{ return if (date != null) dateFormat.format(date) else "" }
    fun toTime(time: LocalTime?): String{ return if (time != null) timeFormat.format(time) else "" }

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            viewModel.onEvent(CreationEvent.EnteredDate(LocalDate.of(year, month + 1, dayOfMonth)))
        },
        dateTextField.value?.year ?: LocalDate.now().year,
        if (dateTextField.value == null) LocalDate.now().monthValue - 1
        else dateTextField.value!!.monthValue - 1,
        dateTextField.value?.dayOfMonth ?: LocalDate.now().dayOfMonth,
    )

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, hourOfDay, minute ->
            viewModel.onEvent(CreationEvent.EnteredTime(LocalTime.of(hourOfDay, minute)))
        },
        timeTextField.value?.hour ?: LocalTime.now().hour,
        timeTextField.value?.minute ?: LocalTime.now().minute,
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
                    value = toDate(dateTextField.value),
                    hint = "Wybierz datę",
                    isError = dateTextField.isError,
                    errorMessage = dateTextField.errorText,
                    leadingIcon = Icons.Rounded.EditCalendar
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


            FormText(text = "Wybierz godzinę")
            Box {
                CreationTextField(
                    value = toTime(timeTextField.value),
                    hint = "Wybierz godzinę",
                    isError = timeTextField.isError,
                    errorMessage = timeTextField.errorText,
                    leadingIcon = Icons.Rounded.Schedule
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

            FormText(text = "Rodzaj zakładu")
            BetSimDropdown(
                value = type.value,
                options = OfferType.entries.map { it.value },
                leadingIcon = { Icon(Icons.Rounded.Handshake, "") }
            ) {
                viewModel.onEvent(CreationEvent.SelectDropdown(offerType = OfferType.entries[it]))
            }
            Spacer(modifier = Modifier.height(20.dp))

            if (type == OfferType.Selection) {
                FormText(text = "Nazwa zakładu")
                CreationTextField(
                    value = offerName.value,
                    hint = "Nazwa zakładu",
                    onValueChange = { viewModel.onEvent(CreationEvent.EnteredName(it)) },
                    isError = offerName.isError,
                    errorMessage = offerName.errorText,
                    leadingIcon = Icons.Rounded.ShortText
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            BetSimButton(text = "Przejdź dalej") {
                viewModel.onEvent(CreationEvent.NavigateFurtherClick)
                if (actionPossible)
                    navController.navigate(Screen.AddGameTeamsScreen.route)
            }

        }
    }

}


@Preview
@Composable
fun CreateOfferScreenPreview(){
    CreateOfferScreen(hiltViewModel(), rememberNavController())
}
package com.example.betsim.presentation.modify.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betsim.data.remote.responses.Odd
import com.example.betsim.presentation.common.components.BetSimOutlinedTextField
import com.example.betsim.presentation.common.util.TextFieldState

@Composable
fun ModifyMatchType(
    options: List<Odd>,
    home: TextFieldState<String>,
    away: TextFieldState<String>,
    onValueChange: (Boolean, String) -> Unit
) {


    val last = options.size - 1
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = options[0].playerName,
                modifier = Modifier
                    .width(130.dp)
                    .padding(bottom = 20.dp)
            )
            BetSimOutlinedTextField(
                value = home.value,
                isError = home.isError,
                supportingText = { Text(home.errorText) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    onValueChange(true, it)
                },
                textStyle = TextStyle(textAlign = TextAlign.Center)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = options[last].playerName,
                modifier = Modifier
                    .width(130.dp)
                    .padding(bottom = 20.dp)
            )
            BetSimOutlinedTextField(
                value = away.value,
                isError = away.isError,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                supportingText = { Text(away.errorText) },
                onValueChange = {
                    onValueChange(false, it)
                },
                textStyle = TextStyle(textAlign = TextAlign.Center)
            )
            Spacer(modifier = Modifier.height(4.dp))

        }
    }
}

@Preview
@Composable
fun ModifyMatchTypePreview(){
    Surface(modifier = Modifier.fillMaxWidth()) {
        ModifyMatchType(
            options = listOf(Odd(0,1.4,"ads"), Odd(1,1.6,"bsd")),
            home = TextFieldState(""),
            away = TextFieldState("")
        ) { _:Boolean, _:String ->
        }
    }
}
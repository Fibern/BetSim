package com.example.betsim.presentation.modify.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.model.Odd
import com.example.betsim.presentation.common.components.BetSimDropdown
import com.example.betsim.presentation.common.util.TextFieldState

@Composable
fun ModifySelectionType(
    options: List<Odd>,
    selected: TextFieldState<Int?>,
    onValueChanged: (Int) -> Unit
){

    Row (
        modifier = Modifier.padding(bottom = 8.dp)
    ){
        BetSimDropdown(
            value = if (selected.value == null) ""
                    else options[selected.value].name,
            isError = selected.isError,
            options = options.map { it.name }
        ) {
            onValueChanged(it)
        }
        Text(
            text = selected.errorText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .padding(horizontal = 56.dp),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(4.dp))
    }
}

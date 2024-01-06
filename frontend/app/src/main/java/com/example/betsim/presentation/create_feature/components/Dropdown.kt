package com.example.betsim.presentation.create_feature.components

import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.betsim.presentation.common.components.BetSimOutlinedTextField

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Dropdown(
    value: String,
    options: List<String>,
    onClick: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {

        BetSimOutlinedTextField(
            value = value,
            readonly = true,
            singleLine = true,
            trailingIcon = {
                if (expanded){
                    IconButton(onClick = { expanded = false }) {
                        Icon(Icons.Filled.ArrowDropUp, "drop_up")
                    }
                }else{
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Filled.ArrowDropDown, "drop_down")
                    }
                }
            },
            onValueChange = {},
            modifier = Modifier
                .menuAnchor(),
            placeholder = { Text(text = "ASDDSA")}
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            options.forEach {

                DropdownMenuItem(
                    text = { Text(text = it) },
                    onClick = {
                        onClick(it)
                        expanded = false
                    }
                )

            }

        }

    }
}
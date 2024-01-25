package com.example.betsim.presentation.common.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BetSimDropdown(
    value: String,
    options: List<String>,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    onClick: (Int) -> Unit,
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
            leadingIcon = leadingIcon,
            isError = isError,
            trailingIcon = {
                if (expanded) {
                    IconButton(onClick = { expanded = false }) {
                        Icon(Icons.Filled.ArrowDropUp, "drop_up")
                    }
                } else {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Filled.ArrowDropDown, "drop_down")
                    }
                }
            },
            onValueChange = {},
            modifier = Modifier
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.heightIn(0.dp, 160.dp)
        ) {

            options.forEachIndexed { i, it ->

                DropdownMenuItem(
                    text = { Text(text = it) },
                    onClick = {
                        onClick(i)
                        expanded = false
                    }
                )

            }

        }
    }
}
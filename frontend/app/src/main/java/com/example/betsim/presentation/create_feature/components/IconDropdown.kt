package com.example.betsim.presentation.create_feature.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.rounded.CropSquare
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.betsim.data.model.EventIcons
import com.example.betsim.presentation.common.components.BetSimOutlinedTextField

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun IconDropdown(
    value: EventIcons?,
    options: Array<EventIcons>,
    onClick: (EventIcons) -> Unit,
    hint: @Composable (() -> Unit)? = null,
    isError: Boolean = false
) {

    var expanded by remember { mutableStateOf(false) }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {

        BetSimOutlinedTextField(
            value = value?.iconName ?: "",
            readonly = true,
            singleLine = true,
            leadingIcon = {
                if (value != null) Icon(value.icon, value.name)
                else {
                    Icon(
                        Icons.Rounded.CropSquare,
                        "",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            },
            isError = isError,
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
            placeholder = hint
        )


        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            options.forEach {

                DropdownMenuItem(
                    leadingIcon = {Icon(it.icon, it.name)},
                    text = { Text(text = it.iconName) },
                    onClick = {
                        onClick(it)
                        expanded = false
                    }
                )

            }

        }

    }
}
package com.example.betsim.presentation.create_feature.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.betsim.presentation.common.components.BetSimOutlinedTextField

@Composable
fun CreationTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    hint: String = "",
    isError: Boolean = false,
    errorMessage: String? = null,
    leadingIcon: ImageVector? = null,
){

    Row(
        modifier = Modifier.wrapContentSize()
    ) {
        BetSimOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = hint)},
            singleLine = true,
            isError = isError,
            supportingText = { if(errorMessage != null) Text(text = errorMessage) },
            trailingIcon = { if (isError) Icon(Icons.Filled.Error, "error") },
            leadingIcon = { if(leadingIcon != null) Icon(leadingIcon, "") }
        )
    }

}
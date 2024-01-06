package com.example.betsim.presentation.create_feature.components

import androidx.compose.runtime.Composable
import com.example.betsim.presentation.common.components.BetSimOutlinedTextField

@Composable
fun CreationTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    hint: @Composable() (() -> Unit)? = null
){


    BetSimOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = hint,
        singleLine = true
    )

}
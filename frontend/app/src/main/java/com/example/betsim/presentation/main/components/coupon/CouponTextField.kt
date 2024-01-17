package com.example.betsim.presentation.main.components.coupon

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.betsim.R

@Composable
fun CouponTextField(
    value: String,
    modifier: Modifier,
    label: @Composable() (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides
                TextSelectionColors(
                    handleColor = colorScheme.onPrimary,
                    backgroundColor = colorScheme.onPrimary.copy(alpha = 0.4f)
                )
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            modifier = modifier,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorScheme.onPrimary,
                focusedTextColor = colorScheme.onPrimary,
                focusedLeadingIconColor = colorScheme.onPrimary,
                focusedLabelColor = colorScheme.onPrimary,
                cursorColor = colorScheme.onPrimary,
                unfocusedBorderColor = colorScheme.onPrimary,
                unfocusedTextColor = colorScheme.onPrimary,
                unfocusedLeadingIconColor = colorScheme.onPrimary,
                unfocusedLabelColor = colorScheme.onPrimary
            ),
            label = label,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_casino_chip),
                    contentDescription = "",
                    tint = colorScheme.onPrimary
                )
            }
        )
    }
}
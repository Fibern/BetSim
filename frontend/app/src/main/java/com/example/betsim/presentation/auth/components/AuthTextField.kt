package com.example.betsim.presentation.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.betsim.common.components.BetSimOutlinedTextField

@Composable
fun AuthTextField(text: String, label: String, onValChange: (String) -> Unit, icon: ImageVector, isPassword: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        BetSimOutlinedTextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp),
            label = { Text(label) },
            onValueChange = { onValChange(it) },
            singleLine = true,
            leadingIcon = { Icon(icon, contentDescription = null) },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions(keyboardType = KeyboardType.Text),
        )

    }
}
package com.example.betsim.presentation.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FormText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .padding(bottom = 4.dp),
        textAlign = TextAlign.Start,
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        lineHeight = MaterialTheme.typography.headlineSmall.lineHeight,
        fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
        letterSpacing = MaterialTheme.typography.headlineSmall.letterSpacing
    )
}
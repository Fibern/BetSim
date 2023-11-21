package com.example.betsim.presentation.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun AuthButton(text: String, onClick: () -> Unit){

    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = text)
    }

}


@Composable
fun AuthClickableText(text: String, coloredText:String, onClick: () -> Unit){

    Text(
        text = buildAnnotatedString {
            append(text)
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append(coloredText)
            }
        },
        modifier = Modifier.clickable {
            onClick()
        }
    )

}
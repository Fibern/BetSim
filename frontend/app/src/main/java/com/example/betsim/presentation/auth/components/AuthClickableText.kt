package com.example.betsim.presentation.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle


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
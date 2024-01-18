package com.example.betsim.presentation.common.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily.Companion.Cursive
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betsim.R.drawable.ic_casino_chip


@Composable
fun SemiTransparentLoadingScreen(){
    val infiniteTransition = rememberInfiniteTransition(label = "Rotate Icon")
    val rotationState by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.primary.copy(alpha = 0.5f))
            .clickable(
                indication = null,
                interactionSource = remember{ MutableInteractionSource() },
                onClick = {}
            )
    ) {

        Text(
            text = "BetSim",
            color = colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(56.dp),
            style = TextStyle(fontFamily = Cursive, fontSize = typography.displayLarge.fontSize)
        )

        Icon(
            painterResource(ic_casino_chip),
            "",
            modifier = Modifier
                .size(56.dp)
                .rotate(rotationState)
                .align(Alignment.Center),
            tint = colorScheme.onPrimary
        )

    }
}



@Preview
@Composable
fun SemiTransparentLoadingScreenPreview(){
    SemiTransparentLoadingScreen()
}
package com.example.betsim.presentation.main.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AnimatedPoints(points: Double){

    var oldPoints by remember {
        mutableDoubleStateOf(points)
    }

    SideEffect {
        oldPoints = points
    }


    val text = "%.2f".format(points).replace('.', ',')
    val oldText = "%.2f".format(oldPoints).replace('.', ',')
    for (i in text.indices){
        val oldChar = oldText.getOrNull(i)
        val newChar = text[i]
        val char = if (oldChar == newChar){
            oldText[i]
        }else{
            text[i]
        }
        AnimatedContent(
            targetState = char,
            label = "text",
            transitionSpec = {
                if (points > oldPoints) {
                    slideInVertically(
                        tween(
                            800,
                            200
                        )
                    ) { -it } togetherWith slideOutVertically(
                        tween(
                            800,
                            200
                        )
                    ) { it }
                } else {
                    slideInVertically(
                        tween(
                            800,
                            200
                        )
                    ) { it } togetherWith slideOutVertically(
                        tween(
                            800,
                            200
                        )
                    ) { -it }
                }
            }
        ) {
            Text(
                text = it.toString(),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
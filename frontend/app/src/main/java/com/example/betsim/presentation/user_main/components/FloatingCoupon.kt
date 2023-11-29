package com.example.betsim.presentation.user_main.components


import android.view.MotionEvent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.model.TournamentGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class NoRippleInteractionSource : MutableInteractionSource{
    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) { }

    override fun tryEmit(interaction: Interaction): Boolean = true

}

@Composable
fun FloatingCoupon(games: List<TournamentGame>) {

    ExtendedFloatingActionButton(
        onClick = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 100.dp, top = 300.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        interactionSource = remember{ NoRippleInteractionSource() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            LazyColumn(){
                items(games){
                    Row {
                        Text(text = it.odds[it.selected.value!!].toString())
                    }
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatAB(onClick: () -> Unit){
    FloatingActionButton(
        onClick = {
            /*TODO*/
            onClick()
        },
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 500.dp, start = 200.dp)
    ){
        BadgedBox(
            badge = {
                Badge {
                    Text(text = "0")
                }
            }
        ) {
            Icon(Icons.Filled.ReceiptLong, "kupon", tint = MaterialTheme.colorScheme.onPrimary )
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OpenedCouponFog(padding: PaddingValues, active: Boolean, onActionDown: () -> Unit){

    AnimatedVisibility(
        visible = active,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            onActionDown()
                        }

                        else -> {}
                    }
                    true
                }
        )
    }
}

@Composable
fun FloatingABAnimation(hidden: Boolean, collapsed: Boolean, games: List<TournamentGame>, onClick: () -> Unit){
    AnimatedVisibility(
        visible = !hidden,
        enter = fadeIn(tween(150)),
        exit = fadeOut(tween(150))
    ) {

        AnimatedContent(
            label = "",
            targetState = collapsed ,
            content = {
                if(it){
                    FloatAB(onClick = ({ onClick() }))
                }else{
                    FloatingCoupon(games)
                }
            },
            transitionSpec = {
                if(hidden)
                    slideIn(
                        initialOffset = { IntOffset(it.width, it.height) }, animationSpec = tween(delayMillis = 500)
                    ) + fadeIn(
                        animationSpec = tween(delayMillis = 500)
                    ) togetherWith slideOut(
                        targetOffset = { IntOffset(it.width, it.height) }, animationSpec = tween(durationMillis = 500)
                    ) + fadeOut(tween(durationMillis = 500))
                else
                    slideIn(
                        initialOffset = { IntOffset(it.width, it.height) }
                    ) + fadeIn(

                    ) togetherWith  slideOut(
                        targetOffset = { IntOffset(it.width, it.height) }, animationSpec = tween(durationMillis = 500)
                    ) + fadeOut(
                        tween(durationMillis = 500)
                    )
            }
        )

    }
}


@Preview
@Composable
fun FloatingCouponPreview(

){

    FloatingCoupon(listOf())

}
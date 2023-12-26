package com.example.betsim.presentation.main.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import com.example.betsim.domain.model.TournamentGame
import com.example.betsim.presentation.main.MainCouponState
import com.example.betsim.presentation.main.components.coupon.FloatingCoupon

@Composable
fun FloatingActionAnimation(
    coupon: MainCouponState,
    onClick: () -> Unit,
    onDeleteClick: (TournamentGame) -> Unit,
    onValueChange: (String) -> Unit,
){
    AnimatedVisibility(
        visible = !coupon.hidden,
        enter = fadeIn(tween(150)),
        exit = fadeOut(tween(150))
    ) {

        AnimatedContent(
            label = "",
            targetState = coupon.collapsed ,
            content = {
                if(it){
                    FloatAB(
                        onClick = ({ onClick() }),
                        count = coupon.games.size
                    )
                }else{
                    FloatingCoupon(
                        coupon = coupon,
                        onValueChange = {str ->
                            onValueChange(str)
                        },
                        onClick = { game ->
                            onDeleteClick(game)
                        }
                    )
                }
            },
            transitionSpec = {
                if(coupon.hidden)
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
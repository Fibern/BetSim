package com.example.betsim.presentation.user_main.components


import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.betsim.common.components.bottomBorder
import com.example.betsim.common.components.topBorder
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.TournamentGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class NoRippleInteractionSource : MutableInteractionSource{
    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) { }

    override fun tryEmit(interaction: Interaction): Boolean = true

}

@Composable
fun FloatingCoupon(games: List<TournamentGame>, oddsValue: String, text: String, onClick: (TournamentGame) -> Unit, onValueChange: (String) -> Unit) {

    ExtendedFloatingActionButton(
        onClick = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 100.dp, top = 300.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        interactionSource = remember{ NoRippleInteractionSource() }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
           Row (
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(24.dp)
                    .bottomBorder(1.dp, MaterialTheme.colorScheme.onPrimary),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Twój kupon", color = MaterialTheme.colorScheme.onPrimary)
            }
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 96.dp, top = 24.dp)
            ){
                LazyColumn{

                    items(games){
                        FloatingCouponItem(game = it){
                            onClick(it)
                        }
                    }

                }
            }
            Row (
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 72.dp)
                    .height(24.dp)
                    .topBorder(1.dp, MaterialTheme.colorScheme.onPrimary),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Łączny kurs",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Text(
                    text = oddsValue,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(end = 40.dp)
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ){
                CompositionLocalProvider(
                    LocalTextSelectionColors provides
                    TextSelectionColors(handleColor = MaterialTheme.colorScheme.onPrimary, backgroundColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f))
                ) {
                    OutlinedTextField(
                        value = text,
                        onValueChange = {
                            onValueChange(it)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                            focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                            cursorColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        //label = {"jd"}
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(bottom = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Text(text = "Postaw", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatAB(onClick: () -> Unit, count: Int){
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
                    Text(text = count.toString())
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
fun FloatingABAnimation(hidden: Boolean, collapsed: Boolean, games: List<TournamentGame>, text: String, oddsValue: String, onClick: () -> Unit, onDeleteClick: (TournamentGame) -> Unit, onValueChange: (String) -> Unit){
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
                    FloatAB(
                        onClick = ({ onClick() }),
                        count = games.size
                    )
                }else{
                    FloatingCoupon(
                        games = games,
                        oddsValue = oddsValue,
                        text = text,
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


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun FCoupon(){
    val state: MutableState<Int?> = mutableStateOf(0)
    val list = listOf(
        TournamentGame(1,"asd","asdd", listOf(Odd(1,"1",1.2), Odd(2,"2",1.4)), selected = state),
        TournamentGame(5,"asd","asdd", listOf(Odd(1,"1",1.2), Odd(2,"2",1.4)), selected = state),
        TournamentGame(4,"asd","asdd", listOf(Odd(1,"1",1.2), Odd(2,"2",1.4)), selected = state),
        TournamentGame(3,"asd","asdd", listOf(Odd(1,"1",1.2), Odd(2,"2",1.4)), selected = state),
        TournamentGame(2,"asd","asdd", listOf(Odd(1,"1",1.2), Odd(2,"2",1.4)), selected = state),
        TournamentGame(7,"asd","asdd", listOf(Odd(1,"1",1.2), Odd(2,"2",1.4)), selected = state),
        TournamentGame(8,"asd","asdd", listOf(Odd(1,"1",1.2), Odd(2,"2",1.4)), selected = state),
    )
    FloatingCoupon(
        games = list, "123", "jsd", onClick = {}, onValueChange = {}
    )
}
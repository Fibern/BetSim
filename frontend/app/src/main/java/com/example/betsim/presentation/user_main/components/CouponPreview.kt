package com.example.betsim.presentation.user_main.components


import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class NoRippleInteractionSource : MutableInteractionSource{
    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) { }

    override fun tryEmit(interaction: Interaction): Boolean = true

}

@Composable
fun CouponPreview() {


    ExtendedFloatingActionButton(
        onClick = {},
        modifier = Modifier
            .width(300.dp)
            .height(500.dp)
        ,
        containerColor = MaterialTheme.colorScheme.primary,
        interactionSource = remember{ NoRippleInteractionSource() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = "coupon")
        }
    }


}






@Preview
@Composable
fun CouponPreview2(

){

    CouponPreview()

}
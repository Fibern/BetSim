package com.example.betsim.presentation.main.components.coupon


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betsim.R.drawable.ic_casino_chip
import com.example.betsim.data.remote.responses.Odd
import com.example.betsim.data.remote.responses.Offer
import com.example.betsim.presentation.main.MainCouponState
import java.time.LocalDateTime


@Composable
fun FloatingCoupon(
    coupon: MainCouponState,
    onClick: (Offer) -> Unit,
    onValueChange: (String) -> Unit,
    onBet: () -> Unit
) {

    ExtendedFloatingActionButton(
        onClick = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 50.dp, top = 250.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        interactionSource = remember{ NoRippleInteractionSource() }
    ) {


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){



           Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Twój kupon", color = MaterialTheme.colorScheme.onPrimary)
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onPrimary)
            )


            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.dp)
                    .weight(1f)
            ){
                LazyColumn{
                    items(coupon.offers){
                        FloatingCouponItem(offer = it){
                            onClick(it)
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onPrimary)
            )


            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Łączny kurs",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Text(
                    text = "%.2f".format(coupon.oddValue),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(end = 40.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {

                CouponTextField(
                    value = coupon.betValue.value,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    label = { Text(text = "Stawka") }
                ) {
                    onValueChange(it)
                }

                CouponButton(
                    text = coupon.winnings,
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 2.dp)
                        .padding(horizontal = 8.dp)
                ) {
                    onBet()
                }

            }

            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = coupon.betValue.errorText,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodySmall
                )
                if (coupon.betValue.errorText.isNotBlank()) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painterResource(ic_casino_chip),
                        "",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun FCoupon(){
    val odd = Offer(true, LocalDateTime.now(), 1, listOf(Odd(1, 1.2, "Team 1"), Odd(2, 1.3, "Team 3")), "", 0, -1, 0, "asd")
    val offers = listOf(odd,odd,odd,odd,odd,odd,odd,odd)
    FloatingCoupon(
        MainCouponState(offers = offers), onClick = {}, onValueChange = {}, {}
    )
}
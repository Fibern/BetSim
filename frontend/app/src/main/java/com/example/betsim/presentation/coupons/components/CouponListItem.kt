package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.betsim.R.drawable.ic_casino_chip
import com.example.betsim.data.remote.responses.Coupon
import com.example.betsim.presentation.common.components.CouponStatus
import java.time.format.DateTimeFormatter


@Composable
fun CouponListItem(coupon: Coupon, modifier: Modifier) {

    val text =
        if (coupon.bets.size > 1) "Kupon łączony"
        else coupon.bets[0].prediction.playerName

    val winnings = (coupon.oddSum * coupon.value)
    val winningsText = when (coupon.status) {
        0 -> "0"
        1 -> "%.2f".format(winnings).replace('.',',')
        else -> '+' + "%.2f".format(winnings).replace('.',',')
    }

    ListItem(
        headlineContent = { Text(text) },
        supportingContent = { Text(DateTimeFormatter.ofPattern("HH:mm:ss").format(coupon.dateTime)) },
        trailingContent = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(winningsText)
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painterResource(id = ic_casino_chip),
                    "",
                )
            }
        },
        leadingContent = {
            CouponStatus.entries[coupon.status].GetIcon()
        },
        modifier = modifier
    )
    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 1.dp)

}
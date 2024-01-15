package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.betsim.R.drawable.ic_casino_chip
import com.example.betsim.domain.model.Coupon
import com.example.betsim.presentation.common.components.CouponStatusIcon
import java.time.format.DateTimeFormatter


@Composable
fun CouponListItem(coupon: Coupon, modifier: Modifier) {

    val text =
        if (coupon.games.size > 1) "Kupon łączony"
        else coupon.games[0].odds[coupon.games[0].selected.value!!].name


    val winnings =
        if (coupon.finished) "+${coupon.winnings}"
        else coupon.winnings.toString()

    ListItem(
        headlineContent = { Text(text) },
        supportingContent = { Text(DateTimeFormatter.ofPattern("HH:mm:ss").format(coupon.date)) },
        trailingContent = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(winnings)
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painterResource(id = ic_casino_chip),
                    "",
                )
            }
        },
        leadingContent = {
            if (coupon.finished){
                if (coupon.winnings == 0.0) CouponStatusIcon.LoseIcon()
                else CouponStatusIcon.WinIcon()
            } else CouponStatusIcon.AwaitIcon()
        },
        modifier = modifier
    )
    Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))

}
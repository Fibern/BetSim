package com.example.betsim.presentation.coupons.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.betsim.presentation.coupons.Category
import java.time.format.DateTimeFormatter


@Composable
fun CouponHeader(
    category: Category
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.onPrimary)
        .padding(vertical = 8.dp)) {
        Text(
            text = DateTimeFormatter.ofPattern("yyyy.MM.dd").format(category.header),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}
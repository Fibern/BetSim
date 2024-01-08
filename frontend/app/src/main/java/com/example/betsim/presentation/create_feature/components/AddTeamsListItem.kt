package com.example.betsim.presentation.create_feature.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.util.OfferType
import com.example.betsim.presentation.common.components.BetSimOutlinedTextField

@Composable
fun AddTeamsListItem(
    offerType: OfferType,
    name: String,
    odd: String,
    onNameChange: (String) -> Unit,
    onChanceChange: (String) -> Unit,
    onRemove: () -> Unit,
    id: Int,
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = (id + 1).toString(),
            modifier = Modifier.width(20.dp),
            textAlign = TextAlign.Center
        )
        BetSimOutlinedTextField(
            readonly = offerType == OfferType.Match && id == 2,
            value = name,
            onValueChange = onNameChange,
            modifier = Modifier.width(160.dp),
            singleLine = true,
            placeholder = { Text(text = "Nazwa") }
        )
        BetSimOutlinedTextField(
            value = odd,
            onValueChange = onChanceChange,
            modifier = Modifier.width(60.dp),
            singleLine = true,
            textStyle = TextStyle(textAlign = TextAlign.Center)
        )
        if (offerType == OfferType.Winner){
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.RemoveCircle,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable(onClick = onRemove)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}
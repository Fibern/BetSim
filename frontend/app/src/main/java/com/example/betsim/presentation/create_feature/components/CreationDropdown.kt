package com.example.betsim.presentation.create_feature.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.betsim.domain.util.OfferType
import com.example.betsim.presentation.common.components.BetSimDropdown

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CreationDropdown(
    value: OfferType,
    options: List<OfferType>,
    onClick: (OfferType) -> Unit
) {



    BetSimDropdown(
        value = value.value,
        options = options.map { it.value }
    ){
        onClick(OfferType.valueOf(it))
    }


}
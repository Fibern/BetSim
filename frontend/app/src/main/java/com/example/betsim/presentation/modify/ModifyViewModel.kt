package com.example.betsim.presentation.modify

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.presentation.create_feature.CreateGameState
import javax.inject.Inject

class ModifyViewModel @Inject constructor(

): ViewModel() {

    val state = mutableStateOf(CreateGameState())

}
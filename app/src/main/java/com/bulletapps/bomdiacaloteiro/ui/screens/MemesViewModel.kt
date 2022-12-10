package com.bulletapps.bomdiacaloteiro.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulletapps.bomdiacaloteiro.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch



class MemesViewModel: ViewModel() {

    val uiState = UIState()

    fun setup() = viewModelScope.launch {
        uiState.memesRef.value = getMemesRef()
    }

    private fun getMemesRef() = listOf(
        R.drawable.img_01,
        R.drawable.img_02,
        R.drawable.img_03,
        R.drawable.img_04,
        R.drawable.img_05,
        R.drawable.img_06,
        R.drawable.img_07,
        R.drawable.img_08,
        R.drawable.img_09,
        R.drawable.img_10,
        R.drawable.img_11,
        R.drawable.img_12,
        R.drawable.img_13,
        R.drawable.img_14,
        R.drawable.img_15,
        R.drawable.img_16,
        R.drawable.img_17,
        R.drawable.img_18,
        R.drawable.img_19,
        R.drawable.img_20,
        R.drawable.img_21,
        R.drawable.img_22,
        R.drawable.img_23,
        R.drawable.img_24,
        R.drawable.img_25,
        R.drawable.img_26,
        R.drawable.img_27,
        R.drawable.img_28,
        R.drawable.img_29,
        R.drawable.img_30,
        R.drawable.img_31,
    )

    class UIState() {
        val memesRef = MutableStateFlow(listOf<Int>())
    }
}
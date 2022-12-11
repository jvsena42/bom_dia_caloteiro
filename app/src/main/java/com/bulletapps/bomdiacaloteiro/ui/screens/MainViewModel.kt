package com.bulletapps.bomdiacaloteiro.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulletapps.bomdiacaloteiro.util.EventFlow
import com.bulletapps.bomdiacaloteiro.util.EventFlowImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel(),
    EventFlow<MainViewModel.Navigation> by EventFlowImpl() {

    fun navigate(navigation: Navigation) {
        viewModelScope.sendEvent(navigation)
    }

    sealed class Navigation(
        val router: String,
        val shouldPop: Boolean = false,
        val popHome: Boolean = false
    ) {
        object SelectMeme : Navigation("select_meme")
        object MessageInfo : Navigation("message_info")
    }
}
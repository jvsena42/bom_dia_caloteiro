package com.bulletapps.bomdiacaloteiro.ui.screens

import androidx.lifecycle.ViewModel
import com.bulletapps.bomdiacaloteiro.util.EventFlow
import com.bulletapps.bomdiacaloteiro.util.EventFlowImpl

class MainViewModel : ViewModel(),
    EventFlow<MainViewModel.Navigation> by EventFlowImpl() {

    sealed class Navigation(
        val router: String,
        val shouldPop: Boolean = false,
        val popHome: Boolean = false
    ) {
        object SelectMeme : Navigation("select_meme")
        object MessageInfo : Navigation("message_info")
    }
}
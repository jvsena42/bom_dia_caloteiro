package com.bulletapps.bomdiacaloteiro.ui.screens.messageInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulletapps.bomdiacaloteiro.R
import com.bulletapps.bomdiacaloteiro.ui.screens.MainViewModel
import com.bulletapps.bomdiacaloteiro.util.EMPTY_STRING
import com.bulletapps.bomdiacaloteiro.util.EventFlow
import com.bulletapps.bomdiacaloteiro.util.EventFlowImpl
import com.bulletapps.bomdiacaloteiro.util.NEGATIVE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MessageInfoViewModel @Inject constructor() : ViewModel(),
    EventFlow<MessageInfoViewModel.ScreenEvents> by EventFlowImpl() {

    val uiState = UIState()

    fun setup(selectedMemeRef: Int) {
        uiState.selectedMemeRef.value = selectedMemeRef
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    fun onAction(action: ScreenActions) = when (action) {
        is ScreenActions.OnClickShare -> {
            viewModelScope.sendEvent(ScreenEvents.SendWhatsappMessage(uiState.selectedMemeRef.value))
        }
        is ScreenActions.OnTextChanged -> {
            onTextChanged(action.fieldText)
        }

    }

    private fun updateFullText() {
        //todo implement
    }

    private fun onTextChanged(fieldText: FieldTexts) = when (fieldText) {
        is FieldTexts.Message -> uiState.message.value = fieldText.text
        is FieldTexts.PixKey -> uiState.pixKey.value = fieldText.text
    }

    sealed interface FieldTexts {
        data class Message(val text: String) : FieldTexts
        data class PixKey(val text: String) : FieldTexts
    }

    class UIState {
        val selectedMemeRef: MutableStateFlow<Int> = MutableStateFlow(NEGATIVE)
        val message: MutableStateFlow<String> = MutableStateFlow(EMPTY_STRING)
        val pixKey: MutableStateFlow<String> = MutableStateFlow(EMPTY_STRING)
        val fullMessage: MutableStateFlow<String> = MutableStateFlow(EMPTY_STRING)
    }

    sealed interface ScreenActions {
        object OnClickShare : ScreenActions
        data class OnTextChanged(val fieldText: FieldTexts) : ScreenActions
    }

    sealed interface ScreenEvents {
        data class SendWhatsappMessage(val ref: Int) : ScreenEvents
    }
}
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

    companion object {
        const val LINE_BREAK = "\n"
        const val INITIAL_MESSAGE = "Bom dia, caloteiro!" //todo MOVE DO STRINGS
        const val PIX_KEY_LABEL = "Chave PIX: "
    }

    val uiState = UIState()

    fun setup(selectedMemeRef: Int?) {
        selectedMemeRef?.let {
            uiState.selectedMemeRef.value = selectedMemeRef
        }
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    fun onAction(action: ScreenActions) = when (action) {
        is ScreenActions.OnClickShare -> {
            viewModelScope.sendEvent(
                ScreenEvents.SendWhatsappMessage(
                    uiState.selectedMemeRef.value,
                    uiState.fullMessage.value
                )
            )
        }
        is ScreenActions.OnTextChanged -> {
            onTextChanged(action.fieldText)
            updateFullText()
        }

    }

    private fun updateFullText() {
        val message =
            uiState.message.value.let { if (it.isNotEmpty()) LINE_BREAK + it else EMPTY_STRING }
        val pixKey =
            uiState.pixKey.value.let { if (it.isNotEmpty()) LINE_BREAK + PIX_KEY_LABEL + it else EMPTY_STRING }
        uiState.fullMessage.value = INITIAL_MESSAGE + message + pixKey
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
        val fullMessage: MutableStateFlow<String> = MutableStateFlow(INITIAL_MESSAGE)
    }

    sealed interface ScreenActions {
        object OnClickShare : ScreenActions
        data class OnTextChanged(val fieldText: FieldTexts) : ScreenActions
    }

    sealed interface ScreenEvents {
        data class SendWhatsappMessage(val imageRef: Int, val text: String) : ScreenEvents
    }
}
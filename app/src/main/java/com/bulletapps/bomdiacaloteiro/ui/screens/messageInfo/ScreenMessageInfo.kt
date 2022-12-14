@file:OptIn(ExperimentalMaterial3Api::class)

package com.bulletapps.bomdiacaloteiro.ui.screens.messageInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulletapps.bomdiacaloteiro.R
import com.bulletapps.bomdiacaloteiro.ui.components.ButtonBottom
import com.bulletapps.bomdiacaloteiro.ui.components.TextTitle
import com.bulletapps.bomdiacaloteiro.ui.screens.messageInfo.MessageInfoViewModel.FieldTexts
import com.bulletapps.bomdiacaloteiro.ui.screens.messageInfo.MessageInfoViewModel.ScreenActions
import com.bulletapps.bomdiacaloteiro.ui.screens.messageInfo.MessageInfoViewModel.ScreenEvents
import com.bulletapps.bomdiacaloteiro.ui.screens.messageInfo.MessageInfoViewModel.UIState
import com.bulletapps.bomdiacaloteiro.ui.theme.BomDiaCaloteiroTheme
import com.bulletapps.bomdiacaloteiro.ui.theme.buttonFontSize
import com.bulletapps.bomdiacaloteiro.ui.theme.buttonHeight
import com.bulletapps.bomdiacaloteiro.ui.theme.horizontalPadding

@Composable
fun ScreenMessageInfo(
    viewModel: MessageInfoViewModel = hiltViewModel(),
    selectedMemeRef: Int?,
    shareContent: (String, Int) -> Unit
) {
    viewModel.setup(selectedMemeRef)
    Screen(uiState = viewModel.uiState, onAction = viewModel::onAction)
    EventConsumer(viewModel = viewModel, shareContent)
}

@Composable
fun EventConsumer(
    viewModel: MessageInfoViewModel,
    shareContent: (String, Int) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is ScreenEvents.SendWhatsappMessage -> shareContent(event.text, event.imageRef)
            }
        }
    }
}

@Composable
fun Screen(
    uiState: UIState,
    onAction: (ScreenActions) -> Unit
) {
    BomDiaCaloteiroTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            TextTitle(text = stringResource(id = R.string.custom_the_text))

            Spacer(modifier = Modifier.height(32.dp))

            MakeFieldMessage(onAction = onAction, uiState = uiState)

            Spacer(modifier = Modifier.height(16.dp))

            MakeFieldPix(onAction = onAction, uiState = uiState)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.preview),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            MakeBoxPreview(onAction = onAction, uiState = uiState)

            Spacer(modifier = Modifier.weight(1f))

            MakeButtonShare(uiState = uiState, onAction = onAction)
        }
    }

}

@Composable
private fun MakeBoxPreview(
    uiState: UIState,
    onAction: (ScreenActions) -> Unit
) {
    val fullMessage by uiState.fullMessage.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = fullMessage,
            fontSize = buttonFontSize,
            maxLines = 10,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontalPadding)
                .fillMaxWidth()
        )
    }
}


@Composable
private fun MakeButtonShare(
    uiState: UIState,
    onAction: (ScreenActions) -> Unit
) {
    ButtonBottom(
        label = stringResource(id = R.string.share),
        action = { onAction.invoke(ScreenActions.OnClickShare) },
    )
}

@Composable
private fun MakeFieldMessage(onAction: (ScreenActions) -> Unit, uiState: UIState) {
    val message by uiState.message.collectAsState()

    OutlinedTextField(
        value = message,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        onValueChange = { onAction(ScreenActions.OnTextChanged(FieldTexts.Message(it))) },
        label = { Text(stringResource(R.string.message)) },
        modifier = Modifier
            .padding(horizontal = horizontalPadding)
            .fillMaxWidth()
    )
}

@Composable
private fun MakeFieldPix(onAction: (ScreenActions) -> Unit, uiState: UIState) {
    val pixKey by uiState.pixKey.collectAsState()

    OutlinedTextField(
        value = pixKey,
        singleLine = true,
        onValueChange = { onAction(ScreenActions.OnTextChanged(FieldTexts.PixKey(it))) },
        label = { Text(stringResource(R.string.pix_key)) },
        modifier = Modifier
            .padding(horizontal = horizontalPadding)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Screen(uiState = UIState(), onAction = { })
}
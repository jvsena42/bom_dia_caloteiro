@file:OptIn(ExperimentalMaterial3Api::class)

package com.bulletapps.bomdiacaloteiro.ui.screens.messageInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulletapps.bomdiacaloteiro.R
import com.bulletapps.bomdiacaloteiro.ui.screens.messageInfo.MessageInfoViewModel.*

@Composable
fun ScreenMessageInfo(
    viewModel: MessageInfoViewModel = hiltViewModel(),
    selectedMemeRef: Int?
) {
    viewModel.setup(selectedMemeRef)
    Screen(uiState = viewModel.uiState, onAction = viewModel::onAction)
}

@Composable
fun Screen(
    uiState: UIState,
    onAction: (ScreenActions) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.custom_the_text),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        MakeFieldMessage(onAction = onAction, uiState = uiState)

        Spacer(modifier = Modifier.height(16.dp))

        MakeFieldPix(onAction = onAction, uiState = uiState)

        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.weight(1f))

        MakeButtonShare(uiState = uiState, onAction = onAction)
    }
}


@Composable
private fun MakeButtonShare(
    uiState: UIState,
    onAction: (ScreenActions) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(Color.Yellow)
            .clickable { onAction.invoke(ScreenActions.OnClickShare) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.share),
            fontSize = 16.sp,
            fontWeight = FontWeight.Black
        )
    }
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
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    )
}

@Composable
private fun MakeFieldPix(onAction: (ScreenActions) -> Unit, uiState: UIState) {
    val pixKey by uiState.pixKey.collectAsState()

    OutlinedTextField(
        value = pixKey,
        singleLine = true,
        onValueChange = { onAction(ScreenActions.OnTextChanged(FieldTexts.Message(it))) },
        label = { Text(stringResource(R.string.pix_key)) },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Screen(uiState = UIState(), onAction = { })
}
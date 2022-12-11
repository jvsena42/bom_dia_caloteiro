package com.bulletapps.bomdiacaloteiro.ui.screens.messageInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulletapps.bomdiacaloteiro.R

@Composable
fun ScreenMessageInfo(
    viewModel: MessageInfoViewModel = hiltViewModel()
) {
    Screen(uiState = viewModel.uiState, onAction = viewModel::onAction)
}

@Composable
fun Screen(
    uiState: MessageInfoViewModel.UIState,
    onAction: (MessageInfoViewModel.ScreenActions) -> Unit
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

        Spacer(modifier = Modifier.weight(1f))

        MakeButtonShare(uiState = uiState, onAction = onAction)
    }
}


@Composable
private fun MakeButtonShare(
    uiState: MessageInfoViewModel.UIState,
    onAction: (MessageInfoViewModel.ScreenActions) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(Color.Yellow)
            .clickable { onAction.invoke(MessageInfoViewModel.ScreenActions.OnClickShare) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.share),
            fontSize = 16.sp,
            fontWeight = FontWeight.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Screen(uiState = MessageInfoViewModel.UIState(), onAction = { })
}
package com.bulletapps.bomdiacaloteiro.ui.screens.selectMeme

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ScreenSelectMeme(
    viewModel: SelectMemeViewModel = hiltViewModel(),
    navigateToMessageInfo: () -> Unit
) {
    Screen(uiState = viewModel.uiState, onAction = viewModel::onAction)
}

@Composable
private fun Screen(
    uiState: SelectMemeViewModel.UIState,
    onAction: (SelectMemeViewModel.ScreenActions) -> Unit
) {

}
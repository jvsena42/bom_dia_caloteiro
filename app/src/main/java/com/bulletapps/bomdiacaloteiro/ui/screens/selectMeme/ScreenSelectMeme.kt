package com.bulletapps.bomdiacaloteiro.ui.screens.selectMeme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulletapps.bomdiacaloteiro.R
import com.bulletapps.bomdiacaloteiro.ui.theme.BomDiaCaloteiroTheme

@Composable
fun ScreenSelectMeme(
    viewModel: SelectMemeViewModel = hiltViewModel(),
    navigateToMessageInfo: () -> Unit
) {
    viewModel.setup()
    Screen(uiState = viewModel.uiState, onAction = viewModel::onAction)
}

@Composable
private fun Screen(
    uiState: SelectMemeViewModel.UIState,
    onAction: (SelectMemeViewModel.ScreenActions) -> Unit
) {
    BomDiaCaloteiroTheme {

        val list by uiState.memesRef.collectAsState()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(id = R.string.select_a_meme),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(list.size) { index ->
                    val item = list[index]

                    Spacer(modifier = Modifier.height(16.dp))

                    MakeItemImage(
                        imageRef = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun MakeItemImage(modifier: Modifier = Modifier, imageRef: Int) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageRef),
            contentDescription = stringResource(id = R.string.select_a_meme),
        )
    }
}


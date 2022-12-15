package com.bulletapps.bomdiacaloteiro.ui.screens.selectMeme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulletapps.bomdiacaloteiro.R
import com.bulletapps.bomdiacaloteiro.ui.components.AppBar
import com.bulletapps.bomdiacaloteiro.ui.components.TextTitle
import com.bulletapps.bomdiacaloteiro.ui.theme.BomDiaCaloteiroTheme
import com.bulletapps.bomdiacaloteiro.ui.theme.imageHeight

@Composable
fun ScreenSelectMeme(
    viewModel: SelectMemeViewModel = hiltViewModel(),
    navigateToMessageInfo: (Int) -> Unit
) {
    viewModel.setup()
    Screen(uiState = viewModel.uiState, onAction = viewModel::onAction)
    EventConsumer(viewModel = viewModel, navigateToMessageInfo = navigateToMessageInfo)
}

@Composable
private fun EventConsumer(
    viewModel: SelectMemeViewModel,
    navigateToMessageInfo: (Int) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is SelectMemeViewModel.ScreenEvents.NavigateMemeDetailScreen -> navigateToMessageInfo.invoke(
                    event.ref
                )
            }
        }
    }
}

@Composable
private fun Screen(
    uiState: SelectMemeViewModel.UIState,
    onAction: (SelectMemeViewModel.ScreenActions) -> Unit
) {
    BomDiaCaloteiroTheme {

        val list by uiState.memesRef.collectAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                item {
                    AppBar(stringResource(id = R.string.select_a_meme))
                }

                items(list.size) { index ->
                    val item = list[index]

                    Spacer(modifier = Modifier.height(24.dp))

                    MakeItemImage(
                        imageRef = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight)
                            .clickable {
                                onAction(
                                    SelectMemeViewModel.ScreenActions.OnImageSelected(
                                        item
                                    )
                                )
                            }
                    )
                }
            }
        )
    }
}

@Composable
fun MakeItemImage(modifier: Modifier = Modifier, imageRef: Int) {
    Box(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageRef),
            contentDescription = stringResource(id = R.string.select_a_meme),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Screen(
        uiState = SelectMemeViewModel.UIState().apply {
            memesRef.value = listOf(
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
            )
        },
        onAction = {}
    )
}


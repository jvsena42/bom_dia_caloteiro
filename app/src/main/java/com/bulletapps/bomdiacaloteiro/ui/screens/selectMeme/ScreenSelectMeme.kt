package com.bulletapps.bomdiacaloteiro.ui.screens.selectMeme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulletapps.bomdiacaloteiro.R
import com.bulletapps.bomdiacaloteiro.ui.theme.BomDiaCaloteiroTheme
import kotlinx.coroutines.flow.collect

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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(id = R.string.select_a_meme),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(list.size) { index ->
                    val item = list[index]

                    Spacer(modifier = Modifier.height(24.dp))

                    MakeItemImage(
                        imageRef = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
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
fun preview() {
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


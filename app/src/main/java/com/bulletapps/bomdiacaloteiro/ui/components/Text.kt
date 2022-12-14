package com.bulletapps.bomdiacaloteiro.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.bulletapps.bomdiacaloteiro.ui.theme.titleFontWeight
import com.bulletapps.bomdiacaloteiro.ui.theme.titleSize

@Composable
fun TextTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = titleSize,
        fontWeight = titleFontWeight
    )
}
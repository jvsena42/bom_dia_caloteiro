package com.bulletapps.bomdiacaloteiro.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulletapps.bomdiacaloteiro.ui.theme.BomDiaCaloteiroTheme
import com.bulletapps.bomdiacaloteiro.ui.theme.buttonFontSize
import com.bulletapps.bomdiacaloteiro.ui.theme.titleFontWeight
import com.bulletapps.bomdiacaloteiro.ui.theme.titleSize

@Composable
fun TextTitle(text: String) {
    BomDiaCaloteiroTheme {
        Text(
            text = text,
            color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.background),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = titleSize,
            fontWeight = titleFontWeight
        )
    }
}

@Composable
fun TextTitleSmall(text: String) {
    BomDiaCaloteiroTheme {
        Text(
            text = text,
            color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.background),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Start,
            fontSize = buttonFontSize,
            fontWeight = FontWeight.Bold
        )
    }
}


package com.bulletapps.bomdiacaloteiro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulletapps.bomdiacaloteiro.ui.theme.BomDiaCaloteiroTheme
import com.bulletapps.bomdiacaloteiro.ui.theme.buttonFontSize
import com.bulletapps.bomdiacaloteiro.ui.theme.buttonHeight

@Composable
fun ButtonBottom(label: String, action: () -> Unit) {
    BomDiaCaloteiroTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .background(color = MaterialTheme.colorScheme.tertiary)
                .clickable { action.invoke() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.tertiary),
                fontSize = buttonFontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
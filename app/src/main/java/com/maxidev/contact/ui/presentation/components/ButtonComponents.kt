package com.maxidev.contact.ui.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maxidev.contact.ui.theme.ContactTheme
import com.maxidev.contact.ui.theme.poppinsFamily

@Composable
fun ButtonTonalComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String
) {
    FilledTonalButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = label,
            fontFamily = poppinsFamily
        )
    }
}

@Composable
fun OutlinedButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = label,
            fontFamily = poppinsFamily
        )
    }
}

@Preview
@Composable
private fun ButtonPreview() {
    ContactTheme {
        Row {
            ButtonTonalComponent(
                onClick = {},
                label = "Preview"
            )
            OutlinedButtonComponent(
                onClick = {},
                label = "Preview"
            )
        }
    }
}
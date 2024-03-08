package com.maxidev.contact.ui.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maxidev.contact.ui.theme.ContactTheme

@Composable
fun ButtonTonalComponent(
    onClick: () -> Unit,
    label: String
) {
    FilledTonalButton(
        modifier = Modifier,
        onClick = onClick
    ) {
        Text(
            text = label
        )
    }
}

@Composable
fun OutlinedButtonComponent(
    onClick: () -> Unit,
    label: String
) {
    OutlinedButton(
        modifier = Modifier,
        onClick = onClick
    ) {
        Text(
            text = label
        )
    }
}

@Preview
@Composable
fun ButtonPreview() {
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
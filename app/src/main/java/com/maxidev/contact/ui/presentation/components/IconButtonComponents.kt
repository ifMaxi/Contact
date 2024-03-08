package com.maxidev.contact.ui.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.maxidev.contact.ui.theme.ContactTheme

@Composable
fun FilledIconButtonComponent(
    onClick: () -> Unit,
    icon: ImageVector
) {
    FilledIconButton(
        modifier = Modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
fun IconButtonComponent(
    onClick: () -> Unit,
    icon: ImageVector
) {
    IconButton(
        modifier = Modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun IconButtonPreview() {
    ContactTheme {
        Row {
            FilledIconButtonComponent(
                onClick = {},
                icon = Icons.Default.Call
            )
            IconButtonComponent(
                onClick = {},
                icon = Icons.Default.Close
            )
        }
    }
}
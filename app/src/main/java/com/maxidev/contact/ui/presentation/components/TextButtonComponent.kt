package com.maxidev.contact.ui.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.maxidev.contact.ui.theme.poppinsFamily

@Composable
fun TextButtonComponent(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = label),
            fontFamily = poppinsFamily
        )
    }
}
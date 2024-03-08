package com.maxidev.contact.ui.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxidev.contact.ui.theme.ContactTheme

@Composable
fun FabComponent(
    onClick: () -> Unit,
    icon: ImageVector,
    @StringRes label: Int
) {
    ExtendedFloatingActionButton(
        modifier = Modifier,
        text = {
            Text(
                text = stringResource(id = label)
            )
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(6.dp)
    )
}

@Composable
fun SmallFabComponent(
    onClick: () -> Unit,
    icon: ImageVector
) {
    SmallFloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(6.dp),
        content = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
    )
}

@Preview
@Composable
private fun FabComponentPreview() {
    ContactTheme {
        Row {
            FabComponent(
                onClick = {},
                icon = Icons.Default.Add,
                label = 0
            )
            SmallFabComponent(
                onClick = {},
                icon = Icons.Default.Delete
            )
        }
    }
}
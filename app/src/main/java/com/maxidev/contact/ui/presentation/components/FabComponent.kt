package com.maxidev.contact.ui.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxidev.contact.R
import com.maxidev.contact.ui.theme.ContactTheme

@Composable
fun FabComponent(
    onClick: () -> Unit,
    @DrawableRes img: Int
) {
    FloatingActionButton(
        modifier = Modifier,
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(6.dp)
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
        )
    }
}

@Preview
@Composable
private fun FabComponentPreview() {
    ContactTheme {
        Row {
            FabComponent(
                onClick = {},
                img = R.drawable.add_contact
            )
        }
    }
}
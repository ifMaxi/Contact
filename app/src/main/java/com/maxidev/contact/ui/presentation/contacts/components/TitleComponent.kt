package com.maxidev.contact.ui.presentation.contacts.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.contact.R
import com.maxidev.contact.ui.presentation.components.ButtonTonalComponent
import com.maxidev.contact.ui.presentation.components.IconButtonComponent
import com.maxidev.contact.ui.theme.ContactTheme

@Composable
fun TitleComponent(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onSave: () -> Unit,
    @StringRes title: Int,
    @StringRes label: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButtonComponent(
            onClick = onCancel,
            icon = Icons.Outlined.Close
        )
        Text(
            text = stringResource(id = title),
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        ButtonTonalComponent(
            onClick = onSave,
            label = stringResource(id = label)
        )
    }
}

@Preview
@Composable
private fun TitlePreview() {
    ContactTheme {
        TitleComponent(
            title = R.string.new_contact,
            onCancel = {},
            onSave = {},
            label = R.string.save
        )
    }
}
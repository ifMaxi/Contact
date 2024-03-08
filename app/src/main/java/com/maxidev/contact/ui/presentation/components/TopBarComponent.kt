package com.maxidev.contact.ui.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.maxidev.contact.ui.theme.ContactTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    @StringRes label: Int
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = label)
            )
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    ContactTheme {
        TopBarComponent(label = 0)
    }
}
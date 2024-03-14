package com.maxidev.contact.ui.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maxidev.contact.ui.theme.ContactTheme

@Composable
fun TextFieldComponent(
    input: String,
    onInputChange: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = input,
        onValueChange = onInputChange,
        singleLine = true,
        label = {
            Text(
                text = label
            )
        },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}

@Preview
@Composable
private fun TextFieldPreview() {
    ContactTheme {
        TextFieldComponent(
            input = "",
            onInputChange = {},
            label = "Lorem Impsum",
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default
        )
    }
}
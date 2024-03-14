package com.maxidev.contact.ui.presentation.contacts.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxidev.contact.R
import com.maxidev.contact.ui.presentation.components.TextFieldComponent
import com.maxidev.contact.ui.presentation.contacts.components.TitleComponent
import com.maxidev.contact.ui.theme.ContactTheme

@Composable
fun ItemContent(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    last: String,
    onLastChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyOptions = KeyboardOptions(imeAction = ImeAction.Next)
    val keyActions = KeyboardActions(
        onNext = { focusManager.moveFocus(FocusDirection.Down) }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        InputFields(
            input = name,
            onInputChange = onNameChange,
            icon = Icons.Outlined.Person,
            label = "Name",
            keyboardOptions = keyOptions,
            keyboardActions = keyActions
        )
        InputFields(
            input = last,
            onInputChange = onLastChange,
            icon = null,
            label = "Lastname",
            keyboardOptions = keyOptions,
            keyboardActions = keyActions
        )
        InputFields(
            input = phone,
            onInputChange = onPhoneChange,
            icon = Icons.Outlined.Phone,
            label = "Phone",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )
    }
}

@Composable
private fun InputFields(
    input: String,
    onInputChange: (String) -> Unit,
    icon: ImageVector?,
    label: String,
    keyboardActions: KeyboardActions,
    keyboardOptions: KeyboardOptions
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .size(50.dp),
            contentAlignment = Alignment.Center
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }
        TextFieldComponent(
            input = input,
            onInputChange = onInputChange,
            label = label,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions
        )
    }
}

@Preview
@Composable
private fun InputPreview() {
    ContactTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            TitleComponent(
                onCancel = {},
                onSave = {},
                title = R.string.new_contact,
                label = R.string.save
            )
            ItemContent(
                name = "",
                onNameChange = {},
                last = "",
                onLastChange = {},
                phone = "",
                onPhoneChange = {},
            )
        }
    }
}
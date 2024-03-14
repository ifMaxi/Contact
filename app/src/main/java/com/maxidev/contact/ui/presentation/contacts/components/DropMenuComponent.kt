package com.maxidev.contact.ui.presentation.contacts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.maxidev.contact.R
import com.maxidev.contact.ui.presentation.components.IconButtonComponent
import com.maxidev.contact.ui.theme.poppinsFamily

@Composable
fun DropMenuComponent(
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val fontFamily = poppinsFamily

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
    ) {
        IconButtonComponent(
            onClick = { expanded = true },
            icon = Icons.Outlined.MoreVert
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.edit_contact),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                    )
                },
                text = {
                    Text(
                        text = stringResource(id = R.string.menu_edit),
                        fontFamily = fontFamily
                    )
                },
                onClick = {
                    onEdit()
                }
            )
            DropdownMenuItem(
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.trash_contact),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                    )
                },
                text = {
                    Text(
                        text = stringResource(id = R.string.menu_delete),
                        fontFamily = fontFamily
                    )
                },
                onClick = {
                    onDelete()
                }
            )
        }
    }
}
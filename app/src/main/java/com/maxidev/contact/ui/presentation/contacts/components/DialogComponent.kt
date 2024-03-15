package com.maxidev.contact.ui.presentation.contacts.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.contact.R
import com.maxidev.contact.ui.presentation.components.FilledIconButtonComponent
import com.maxidev.contact.ui.presentation.components.TextButtonComponent
import com.maxidev.contact.ui.theme.ContactTheme
import com.maxidev.contact.ui.theme.poppinsFamily

@Composable
fun DialogComponent(
    modifier: Modifier = Modifier,
    onCall: () -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    name: String?,
    lastName: String?,
    phone: String?
) {
    val fontFamily = poppinsFamily

    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        dismissButton = {
            TextButtonComponent(
                label = R.string.menu_delete,
                onClick = {
                    onDismiss()
                    onDelete()
                }
            )
        },
        confirmButton = {
            TextButtonComponent(
                label = R.string.dialog_confirm,
                onClick = { onConfirm() }
            )
        },
        icon = {
            Box(
                modifier = Modifier
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(4.dp)
                    .size(70.dp)
                    .border(
                        BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline
                        ),
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name?.first().toString(),
                    fontFamily = fontFamily,
                    fontSize = 35.sp
                )
            }
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${name ?: ""} ${lastName ?: ""}",
                    fontFamily = fontFamily,
                    fontSize = 25.sp
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Call,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = phone ?: "",
                        fontFamily = fontFamily,
                        fontSize = 20.sp
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledIconButtonComponent(
                        onClick = { onEdit() },
                        icon = Icons.Outlined.Edit
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    FilledIconButtonComponent(
                        onClick = { onCall() },
                        icon = Icons.Outlined.Call
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun DialogPreview() {
    ContactTheme {
        DialogComponent(
            onCall = {},
            onConfirm = {},
            onDismiss = {},
            name = "Lorem",
            lastName = "Impsum",
            phone = "+54 0123-456789",
            onDelete = {},
            onEdit = {}
        )
    }
}
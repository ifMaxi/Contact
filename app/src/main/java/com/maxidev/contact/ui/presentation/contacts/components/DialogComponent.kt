package com.maxidev.contact.ui.presentation.contacts.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.maxidev.contact.ui.presentation.components.FilledIconButtonComponent

@Composable
fun DialogComponent(
    modifier: Modifier = Modifier,
    onCall: () -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    name: String?,
    lastName: String?,
    phone: String?
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() }
            ) {
                Text(
                    text = "Confirm"
                )
            }
        },
        icon = {
            Box(
                modifier = Modifier
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(4.dp)
                    .size(90.dp)
                    .border(
                        BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        ),
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name?.first().toString()
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
                    text = "${name ?: ""} ${lastName ?: ""}"
                )
            }
        },
        text = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = phone ?: ""
                )
                FilledIconButtonComponent(
                    onClick = { onCall() },
                    icon = Icons.Outlined.Call
                )
            }
        }
    )
}
package com.maxidev.contact.ui.presentation.contacts.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.maxidev.contact.ui.theme.ContactTheme

@Composable
fun ContactCard(
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    name: String,
    lastName: String,
    phone: String
) {
    val openDialog  = remember { mutableStateOf(false) }
    val intentCall = Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:$phone"))
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp)
            .clickable { openDialog.value = true },
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(4.dp)
                .size(40.dp)
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
                text = name.first().toString(),
                fontSize = 25.sp
            )
        }
        Text(
            text = name,
            fontSize = 18.sp
        )
    }

    if (openDialog.value) {
        DialogComponent(
            onCall = { ContextCompat.startActivity(context, intentCall, null) },
            onConfirm = { openDialog.value = false },
            onDismiss = { openDialog.value = false },
            onDelete = {
                openDialog.value = false
                onDelete()
            },
            onEdit = {
                openDialog.value = false
                onEdit()
            },
            name = name,
            lastName = lastName,
            phone = phone
        )
    }
}

@Preview
@Composable
private fun MainPreview() {
    ContactTheme {
        ContactCard(
            name = "Preview",
            onDelete = {},
            onEdit = {},
            lastName = "",
            phone = ""
        )
    }
}
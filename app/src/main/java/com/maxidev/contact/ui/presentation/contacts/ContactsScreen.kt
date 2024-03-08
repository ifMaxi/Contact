package com.maxidev.contact.ui.presentation.contacts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.contact.R
import com.maxidev.contact.data.local.entity.ContactEntity
import com.maxidev.contact.ui.presentation.components.FabComponent
import com.maxidev.contact.ui.presentation.components.SmallFabComponent
import com.maxidev.contact.ui.presentation.components.TopBarComponent
import com.maxidev.contact.ui.presentation.contacts.components.DropMenuComponent
import com.maxidev.contact.ui.theme.ContactTheme

@Composable
fun ContactScreen(
    viewModel: ContactViewModel,
    onAdd: () -> Unit,
    onEdit: (ContactEntity) -> Unit
) {
    val content by viewModel.contentState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopBarComponent(label = R.string.contacts)
        },
        floatingActionButton = {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.End
            ) {
                SmallFabComponent(
                    onClick = { viewModel.deleteAllContact() },
                    icon = Icons.Outlined.Delete
                )
                FabComponent(
                    onClick = { onAdd() },
                    icon = Icons.Outlined.Add,
                    label = R.string.new_contact
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        ListContent(
            modifier = Modifier.padding(paddingValues),
            onDelete = { viewModel.deleteContact(it) },
            onEdit = { onEdit(it) },
            contact = content.listContent
        )
    }
}

@Composable
private fun ListContent(
    modifier: Modifier = Modifier,
    onDelete: (ContactEntity) -> Unit,
    onEdit: (ContactEntity) -> Unit,
    contact: List<ContactEntity>
) {
    val lazyState = rememberLazyListState()
    val rememberContact = remember(contact) { contact }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        state = lazyState,
        contentPadding = PaddingValues(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = rememberContact,
            key = { it.id },
            contentType = { it.id }
        ) { items ->
            ContactCard(
                onDelete = { onDelete(items) },
                onEdit = { onEdit(items) },
                name = items.name ?: ""
            )
        }
    }
}

@Composable
private fun ContactCard(
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    name: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(4.dp)
                    .shadow(
                        elevation = 6.dp,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.person_contact),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(
                            BorderStroke(
                                width = 1.dp,
                                color = Color.Black
                            ),
                            shape = CircleShape
                        )
                )
            }
            Text(
                text = name
            )
            Spacer(modifier = Modifier.weight(1f))
            DropMenuComponent(
                onDelete = {
                    onDelete()
                },
                onEdit = {
                    onEdit()
                }
            )
        }
    }
}

@Preview
@Composable
private fun MainPreview() {
    ContactTheme {
        ContactCard(
            name = "Preview", onDelete = {}, onEdit = {},
        )
    }
}
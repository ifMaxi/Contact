package com.maxidev.contact.ui.presentation.contacts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.contact.R
import com.maxidev.contact.data.local.entity.ContactEntity
import com.maxidev.contact.ui.presentation.components.FabComponent
import com.maxidev.contact.ui.presentation.components.SmallFabComponent
import com.maxidev.contact.ui.presentation.components.TopBarComponent
import com.maxidev.contact.ui.presentation.contacts.components.ContactCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    viewModel: ContactViewModel,
    onAdd: () -> Unit,
    onEdit: (ContactEntity) -> Unit
) {
    val content by viewModel.contentState.collectAsStateWithLifecycle()
    val sq by viewModel.searchContact.collectAsStateWithLifecycle()
    val query by viewModel.query
    var active by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                TopBarComponent(label = R.string.contacts)
                DockedSearchBar(
                    query = query,
                    onQueryChange = viewModel::onQueryChange,
                    onSearch = {
                        active = false
                        viewModel.searchContact(it)
                    },
                    active = active,
                    onActiveChange = { active = true }
                ) {
                    // Do something
                }
            }
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
            contact = if (query.isEmpty()) content.listContent else sq
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
                name = items.name ?: "",
                lastName = items.lastName ?: "",
                phone = items.phone ?: ""
            )
        }
    }
}
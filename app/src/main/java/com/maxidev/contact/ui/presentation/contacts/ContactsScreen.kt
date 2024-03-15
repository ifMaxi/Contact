package com.maxidev.contact.ui.presentation.contacts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.contact.R
import com.maxidev.contact.data.local.entity.ContactEntity
import com.maxidev.contact.ui.presentation.components.FabComponent
import com.maxidev.contact.ui.presentation.components.TopBarComponent
import com.maxidev.contact.ui.presentation.contacts.components.ContactCard
import com.maxidev.contact.ui.presentation.contacts.components.SearchBarComponent
import kotlinx.coroutines.launch

@Composable
fun ContactScreen(
    viewModel: ContactViewModel,
    onAdd: () -> Unit,
    onEdit: (ContactEntity) -> Unit
) {
    val content by viewModel.contentState.collectAsStateWithLifecycle()
    val searchContacts by viewModel.searchContact.collectAsStateWithLifecycle()
    val query by viewModel.query
    var active by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        },
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                TopBarComponent(label = R.string.contacts)
                SearchBarComponent(
                    query = query,
                    onQueryChange = viewModel::onQueryChange,
                    onSearching = {
                        scope.launch {
                            if (query.isEmpty()) {
                                active = false
                            } else {
                                active = false
                                viewModel.searchContact(it)
                            }
                        }
                        focusManager.clearFocus()
                    },
                    active = active,
                    onActiveChange = {
                        active = false
                    },
                    onClearText = {
                        viewModel.onQueryChange("")
                        focusManager.clearFocus()
                    }
                )
            }
        },
        floatingActionButton = {
            FabComponent(
                onClick = { onAdd() },
                img = R.drawable.add_contact
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        ListContent(
            modifier = Modifier.padding(paddingValues),
            onDelete = {
                viewModel.deleteContact(it)
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = "Contact deleted",
                        actionLabel = "Ok",
                        duration = SnackbarDuration.Short
                    )
                }
            },
            onEdit = { onEdit(it) },
            contact = when {
                query.isEmpty() -> content.listContent
                else -> searchContacts
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ListContent(
    modifier: Modifier = Modifier,
    onDelete: (ContactEntity) -> Unit,
    onEdit: (ContactEntity) -> Unit,
    contact: List<ContactEntity>
) {
    val lazyState = rememberLazyListState()
    val rememberContact = remember(contact) { contact }
    val grouped = rememberContact.groupBy { it.name?.first().toString() }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        state = lazyState,
        contentPadding = PaddingValues(20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        grouped.forEach { (name, collectionName) ->
            stickyHeader {
                Surface(
                    modifier = Modifier
                        .fillParentMaxWidth()
                ) {
                    Text(
                        text = name.first().toString(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }

            items(
                items = collectionName,
                key = { it.id }
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
}
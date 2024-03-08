package com.maxidev.contact.ui.presentation.contacts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.maxidev.contact.R
import com.maxidev.contact.data.local.entity.ContactEntity
import com.maxidev.contact.ui.presentation.contacts.components.TitleComponent
import com.maxidev.contact.ui.presentation.contacts.item.ItemContent

@Composable
fun EditContactScreen(
    viewModel: ContactViewModel,
    popBack: () -> Unit,
    id: Long,
    name: String?,
    lastName: String?,
    phone: String?
) {
    var names by remember { mutableStateOf(name) }
    var lastNames by remember { mutableStateOf(lastName) }
    var phones by remember { mutableStateOf(phone) }

    LaunchedEffect(key1 = Unit) {
        viewModel.contactById(id)
    }

    Scaffold(
        topBar = {
            TitleComponent(
                onCancel = popBack,
                onSave = {
                    val entity = ContactEntity(
                        id = id,
                        name = names ?: "",
                        lastName = lastNames ?: "",
                        phone = phones ?: ""
                    )

                    viewModel.upsertContact(entity)
                    popBack()
                },
                title = R.string.edit_contact,
                label = R.string.edit
            )
        }
    ) { paddingValues ->
        ItemContent(
            modifier = Modifier.padding(paddingValues),
            name = names ?: "",
            onNameChange = { names = it },
            last = lastNames ?: "",
            onLastChange = { lastNames = it },
            phone = phones ?: "",
            onPhoneChange = { phones = it },
        )
    }
}
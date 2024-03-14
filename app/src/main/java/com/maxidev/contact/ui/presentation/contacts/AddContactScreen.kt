package com.maxidev.contact.ui.presentation.contacts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.maxidev.contact.R
import com.maxidev.contact.data.local.entity.ContactEntity
import com.maxidev.contact.ui.presentation.contacts.components.TitleComponent
import com.maxidev.contact.ui.presentation.contacts.item.ItemContent

@Composable
fun AddContactScreen(
    viewModel: ContactViewModel,
    popBack: () -> Unit,
) {
    val name by viewModel.state.name
    val lastName by viewModel.state.lastName
    val phone by viewModel.state.phone

    Scaffold(
        topBar = {
            TitleComponent(
                onCancel = popBack,
                onSave = {
                    val entity = ContactEntity(
                        id = viewModel.state.id,
                        name = viewModel.state.name.value,
                        lastName = viewModel.state.lastName.value,
                        phone = viewModel.state.phone.value
                    )

                    viewModel.upsertContact(entity)
                    popBack()
                },
                title = R.string.new_contact,
                label = R.string.save
            )
        }
    ) { paddingValues ->
        ItemContent(
            modifier = Modifier.padding(paddingValues),
            name = name ?: "",
            onNameChange = viewModel::onNameChanged,
            last = lastName ?: "",
            onLastChange = viewModel::onLastNameChanged,
            phone = phone ?: "",
            onPhoneChange = viewModel::onPhoneChanged,
        )
    }
}


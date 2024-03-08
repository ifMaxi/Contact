package com.maxidev.contact.ui.presentation.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.contact.data.local.entity.ContactEntity
import com.maxidev.contact.data.repository.impl.ContactRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repository: ContactRepositoryImpl
): ViewModel() {

    private val ioDispatcher = Dispatchers.IO

    private val _state = MutableStateFlow(ContactState())
    val state = _state.value

    fun onNameChanged(newName: String) {
        _state.value.name.value = newName
    }

    fun onLastNameChanged(newLastName: String) {
        _state.value.lastName.value = newLastName
    }

    fun onPhoneChanged(newPhone: String) {
        _state.value.phone.value = newPhone
    }

    fun contactById(id: Long) = repository.getContactsById(id)

    val contentState: StateFlow<ContactState> =
        repository.getAllContacts().map { ContactState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = ContactState()
            )

    fun upsertContact(contact: ContactEntity) {
//        val entity = ContactEntity(
//            id = _state.value.id,
//            name = _state.value.name.value,
//            lastName = _state.value.lastName.value,
//            phone = _state.value.phone.value
//        )

        viewModelScope.launch(ioDispatcher) {
            repository.upsert(contact)
        }
    }

    fun deleteContact(contact: ContactEntity) {
        viewModelScope.launch(ioDispatcher) {
            repository.delete(contact)
        }
    }

    fun deleteAllContact() {
        viewModelScope.launch(ioDispatcher) {
            repository.deleteAll()
        }
    }
}
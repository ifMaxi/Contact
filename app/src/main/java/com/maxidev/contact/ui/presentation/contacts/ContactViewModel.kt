package com.maxidev.contact.ui.presentation.contacts

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.contact.data.local.entity.ContactEntity
import com.maxidev.contact.data.repository.impl.ContactRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repository: ContactRepositoryImpl
): ViewModel() {

    private val ioDispatcher = Dispatchers.IO

    private val _state = MutableStateFlow(ContactState())
    val state = _state.value

    private val _query: MutableState<String> = mutableStateOf("")
    val query: State<String> = _query

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

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

    private val _searchContact = MutableStateFlow<List<ContactEntity>>(emptyList())
    val searchContact = _searchContact

    fun searchContact(query: String) {
        viewModelScope.launch {
            repository.getContactsByName(query)
                .debounce(300)
                .collect { _searchContact.value = it }
        }
    }
}
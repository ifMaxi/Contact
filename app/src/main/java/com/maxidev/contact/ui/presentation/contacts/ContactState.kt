package com.maxidev.contact.ui.presentation.contacts

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.maxidev.contact.data.local.entity.ContactEntity

data class ContactState(
    val listContent: List<ContactEntity> = emptyList(),
    var id: Long = 0L,
    var name: MutableState<String?> = mutableStateOf(""),
    var lastName: MutableState<String?> = mutableStateOf(""),
    var phone: MutableState<String?> = mutableStateOf("")
)

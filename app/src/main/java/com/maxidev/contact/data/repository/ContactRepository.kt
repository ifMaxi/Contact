package com.maxidev.contact.data.repository

import com.maxidev.contact.data.local.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getAllContacts(): Flow<List<ContactEntity>>

    fun getContactsByName(query: String): Flow<List<ContactEntity>>

    fun getContactsById(id: Long): Flow<List<ContactEntity>>

    suspend fun upsert(contact: ContactEntity)

    suspend fun delete(contact: ContactEntity)
}
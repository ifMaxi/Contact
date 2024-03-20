package com.maxidev.contact.data.repository.impl

import com.maxidev.contact.data.local.dao.ContactDao
import com.maxidev.contact.data.local.entity.ContactEntity
import com.maxidev.contact.data.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactDao: ContactDao
): ContactRepository {
    override fun getAllContacts(): Flow<List<ContactEntity>> =
        contactDao.getAllContacts()

    override fun getContactsByName(query: String): Flow<List<ContactEntity>> =
        contactDao.getContactsByName(query)

    override fun getContactsById(id: Long): Flow<List<ContactEntity>> =
        contactDao.getContactById(id)

    override suspend fun upsert(contact: ContactEntity) =
        contactDao.upsertContact(contact)

    override suspend fun delete(contact: ContactEntity) =
        contactDao.deleteContact(contact)
}
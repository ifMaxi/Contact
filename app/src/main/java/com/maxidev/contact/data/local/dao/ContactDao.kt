package com.maxidev.contact.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.contact.data.local.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE contacts.name LIKE '%' || :name || '%'")
    fun getContactsByName(name: String): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE id=:id")
    fun getContactById(id: Long): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts ORDER BY name ASC")
    fun sortContactByName(): Flow<List<ContactEntity>>

    @Upsert
    suspend fun upsertContact(contact: ContactEntity)

    @Delete
    suspend fun deleteContact(contact: ContactEntity)

    @Query("DELETE FROM contacts")
    suspend fun deleteAllContacts()
}
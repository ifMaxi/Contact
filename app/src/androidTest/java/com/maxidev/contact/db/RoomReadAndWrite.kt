package com.maxidev.contact.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxidev.contact.data.local.ContactDataBase
import com.maxidev.contact.data.local.dao.ContactDao
import com.maxidev.contact.data.local.entity.ContactEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomReadAndWrite {
    private lateinit var dao: ContactDao
    private lateinit var dataBase: ContactDataBase

    private val contact1 = ContactEntity(id = 1, name = "Test", lastName = "Database", phone = "404")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        dataBase = Room.inMemoryDatabaseBuilder(context = context, ContactDataBase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = dataBase.contactDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        dataBase.close()
    }

    private suspend fun insertOneItemInTheDataBase() {
        dao.upsertContact(contact1)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsertIntoTheDataBase() = runBlocking {
        insertOneItemInTheDataBase()

        val allContacts = dao.getAllContacts().first()

        Assert.assertEquals(allContacts[0], contact1)
    }

    @Test
    @Throws(Exception::class)
    fun getOneContactById() = runBlocking {
        insertOneItemInTheDataBase()

        val byId = dao.getContactById(contact1.id).first()

        Assert.assertEquals(byId[0], contact1)
    }

    @Test
    @Throws(Exception::class)
    fun deleteContactInTheDataBase() = runBlocking {
        insertOneItemInTheDataBase()

        val deleteContact = dao.deleteContact(contact1)

        Assert.assertEquals(deleteContact, Unit)
    }

    @Test
    @Throws(Exception::class)
    fun editOneContactInTheDataBase() = runBlocking {
        insertOneItemInTheDataBase()

        val itemUpdated = ContactEntity(1, "Update", "Up", "101")

        dao.upsertContact(itemUpdated)

        val result = dao.getAllContacts().first()

        Assert.assertEquals(result[0].name, itemUpdated.name)
    }
}
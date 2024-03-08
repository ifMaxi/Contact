package com.maxidev.contact.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maxidev.contact.data.local.dao.ContactDao
import com.maxidev.contact.data.local.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 3, exportSchema = false)
abstract class ContactDataBase: RoomDatabase() {
    abstract fun contactDao(): ContactDao
}
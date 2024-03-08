package com.maxidev.contact.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String?,
    val lastName: String?,
    val phone: String?
)
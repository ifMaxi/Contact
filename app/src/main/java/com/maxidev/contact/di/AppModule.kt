package com.maxidev.contact.di

import android.content.Context
import androidx.room.Room
import com.maxidev.contact.data.local.ContactDataBase
import com.maxidev.contact.data.local.dao.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesDataBase(
        @ApplicationContext context: Context
    ): ContactDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = ContactDataBase::class.java,
            name = "contact-data-base"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesDao(dataBase: ContactDataBase): ContactDao =
        dataBase.contactDao()
}
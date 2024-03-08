package com.maxidev.contact.di

import com.maxidev.contact.data.local.dao.ContactDao
import com.maxidev.contact.data.repository.impl.ContactRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesContactRepository(dao: ContactDao): ContactRepositoryImpl =
        ContactRepositoryImpl(dao)
}
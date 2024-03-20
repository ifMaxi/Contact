package com.maxidev.contact.ui.presentation.contacts

import com.maxidev.contact.data.local.entity.ContactEntity
import com.maxidev.contact.data.repository.ContactRepository
import com.maxidev.contact.rules.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var repository: ContactRepository
    private lateinit var viewModel: ContactViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coroutineTestRule.testDispatcher

        viewModel = spyk(ContactViewModel(repository))
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `check if the function obtain a contact by its id is called`() = runTest {
        val id = 1L
        val contact = ContactEntity(id = id, name = "Unit", lastName = "Test", phone = "404")

        coEvery { repository.getContactsById(id) } returns flow { contact.id }

        viewModel.contactById(id)

        coVerify { viewModel.contactById(contact.id) }
    }
}
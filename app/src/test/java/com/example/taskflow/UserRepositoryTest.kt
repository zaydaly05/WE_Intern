package com.example.taskflow


import com.example.taskflow.data.repository.UserRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class UserRepositoryTest {

    private val repository = UserRepositoryImpl()

    @Test
    fun `getUsers returns expected users`() = runTest {

        val users = repository.getUsers().first()

        assertEquals(3, users.size)

        assertEquals("Zaid", users[0].name)
        assertEquals("Ahmed", users[1].name)
        assertEquals("Mohamed", users[2].name)
    }
}
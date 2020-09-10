package com.xently.models

import com.xently.models.User.AuthProvider.EMAIL
import com.xently.models.User.AuthProvider.GOOGLE
import org.junit.Assert.assertEquals
import org.junit.Test

class UserTest {

    @Test
    fun defaultFieldValues() {
        assertEquals(false, User().isVerified)
        assertEquals(true, User(provider = GOOGLE).isVerified)
        assertEquals(EMAIL, User().provider)
    }

    @Test
    fun getName() {
        assertEquals(null, User().name)
        assertEquals(null, User(firstName = "", lastName = "").name)
        assertEquals(null, User(firstName = "", lastName = "    ").name)
        assertEquals(null, User(firstName = "   ", lastName = "").name)
        assertEquals(null, User(firstName = "   ", lastName = "   ").name)
        assertEquals("John", User(firstName = "John").name)
        assertEquals("Doe", User(lastName = "Doe").name)
        assertEquals("John Doe", User(firstName = "John", lastName = "Doe").name)
    }
}
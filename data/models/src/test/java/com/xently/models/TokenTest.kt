package com.xently.models

import com.xently.common.utils.JSON_CONVERTER
import com.xently.common.utils.web.AuthScheme
import org.junit.Assert.assertEquals
import org.junit.Test

class TokenTest {
    @Test
    fun defaultFieldValues() {
        val token = Token("abc")
        assertEquals("abc", token.access)
        assertEquals(token.access, token.refresh)
        assertEquals(1L, token.id)
    }

    @Test
    fun tokenToString() {
        assertEquals("Bearer ", Token().toString())
        assertEquals("Bearer abc", Token("abc").toString())
        assertEquals("Bearer abc", "${Token("abc")}")
        assertEquals("Basic abc", "${Token("abc", scheme = AuthScheme.BASIC)}")
    }

    @Test
    fun tokenToJson() {
        val token = Token("abc", "def")
        assertEquals(
            """{"access" : "abc","refresh": "def"}""".replace(Regex("(\\s)*:(\\s)*"), ":"),
            JSON_CONVERTER.toJson(token),
        )
    }
}
package com.xently.data.source.local.daos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.xently.data.source.local.AssistantDatabase
import com.xently.models.DEFAULT_TOKEN_ID
import com.xently.models.Token
import com.xently.tests.unit.rules.MainCoroutineRule
import com.xently.tests.unit.rules.RoomDatabaseRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28])
class TokenDAOTest {
    @get:Rule
    val databaseRule = RoomDatabaseRule(AssistantDatabase::class.java)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var dao: TokenDAO

    @Before
    fun setUp() {
        dao = databaseRule.database.tokenDAO
    }

    @Test
    fun addToken() = runBlockingTest {
        assertEquals(1L, dao.addToken(Token()))
    }

    @Test
    fun getToken() = runBlockingTest {
        with(dao) {
            val token = Token()
            getToken(token.id).test {
                assertNull(expectItem())
            }
            addToken(token)
            // Get token with existing(created above) id
            getToken(token.id).test {
                val dbToken = expectItem()
                assertEquals(token, dbToken)
                assertEquals(token.id, dbToken!!.id)
            }
            // Get token with un-existing id
            getToken(DEFAULT_TOKEN_ID + 1).test {
                assertNull(expectItem())
            }
        }
    }
}
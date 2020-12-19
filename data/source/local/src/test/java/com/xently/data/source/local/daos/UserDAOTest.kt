package com.xently.data.source.local.daos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.xently.data.source.local.AssistantDatabase
import com.xently.models.user.User
import com.xently.tests.unit.rules.MainCoroutineRule
import com.xently.tests.unit.rules.RoomDatabaseRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28])
class UserDAOTest {
    @get:Rule
    val databaseRule = RoomDatabaseRule(AssistantDatabase::class.java)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var dao: UserDAO

    @Before
    fun setUp() {
        dao = databaseRule.database.userDAO
    }

    @Test
    fun addUser() = runBlockingTest {
        assertEquals(1L, dao.addUser(User()))
    }

    @Test
    fun getUser() = runBlockingTest {
        with(dao) {
            val user = User()
            getUser(user.id).test {
                assertNull(expectItem())
            }
            // Get user with existing(created above) id
            addUser(user)
            getUser(user.id).test {
                val dbUser = expectItem()
                assertNotNull(dbUser)
                assertEquals(user, dbUser)
            }
            // Get user with un-existing id
            getUser(user.id + 1).test {
                assertNull(expectItem())
            }
        }
    }
}
package com.xently.user.common.repository

import android.content.SharedPreferences
import com.xently.user.common.SHARED_PREF_SKIP_AUTH_VALUE_KEY
import com.xently.user.common.source.IUserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

abstract class AbstractUserRepository(private val preference: SharedPreferences) : IUserDataSource {
    open val skipAuthentication: Flow<Boolean>
        get() = flowOf(preference.getBoolean(SHARED_PREF_SKIP_AUTH_VALUE_KEY, false))

    open fun skipAuthentication(skip: Boolean = false): Flow<Boolean> = flow {
        with(preference.edit()) {
            putBoolean(SHARED_PREF_SKIP_AUTH_VALUE_KEY, skip)
            emit(commit() && skip)
        }
    }
}
package com.xently.shoppingassistant.ui

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.xently.common.di.qualifiers.UnencryptedSharedPreference
import com.xently.shoppingassistant.R
import com.xently.user.common.SHARED_PREF_SKIP_AUTH_VALUE_KEY
import com.xently.user.common.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class SplashViewModel @ViewModelInject constructor(
    private val repository: IUserRepository,
    @UnencryptedSharedPreference
    private val preferences: SharedPreferences,
) : ViewModel() {
    val navDestination: Flow<Int>
        get() = repository.getUser().mapLatest {
            if (it == null) {
                val skipAuth = preferences.getBoolean(SHARED_PREF_SKIP_AUTH_VALUE_KEY, false)
                if (skipAuth) {
                    R.id.dest_splash // navigate to home page
                } else {
                    R.id.dest_splash // navigate to auth provider fragment
                }
            } else {
                if (it.isVerified) {
                    R.id.dest_splash // navigate to home page
                } else {
                    R.id.dest_splash // navigate to verification page
                }
            }
        }
}
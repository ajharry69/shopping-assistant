package com.xently.shoppingassistant.ui

import androidx.hilt.lifecycle.ViewModelInject
import com.xently.shoppingassistant.R
import com.xently.user.common.AbstractUserViewModel
import com.xently.user.common.repository.AbstractUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class SplashViewModel @ViewModelInject constructor(repository: AbstractUserRepository) :
    AbstractUserViewModel(repository) {
    val navDestination: Flow<Int>
        get() = getUser().combine(skipAuthentication) { user, skipAuth ->
            if (user == null) {
                if (skipAuth) {
                    R.id.dest_splash // navigate to home page
                } else {
                    R.id.dest_splash // navigate to auth provider fragment
                }
            } else {
                if (user.isVerified) {
                    R.id.dest_splash // navigate to home page
                } else {
                    R.id.dest_splash // navigate to verification page
                }
            }
        }
}
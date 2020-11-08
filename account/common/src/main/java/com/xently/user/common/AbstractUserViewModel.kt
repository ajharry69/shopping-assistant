package com.xently.user.common

import androidx.lifecycle.ViewModel
import com.xently.models.user.DEFAULT_USER_ID
import com.xently.user.common.repository.AbstractUserRepository
import kotlinx.coroutines.flow.Flow

abstract class AbstractUserViewModel(private val repository: AbstractUserRepository) : ViewModel() {
    val skipAuthentication: Flow<Boolean> = repository.skipAuthentication
    fun skipAuthentication(skip: Boolean = false) = repository.skipAuthentication(skip)
    fun getUser(id: Long = DEFAULT_USER_ID) = repository.getUser(id)
}
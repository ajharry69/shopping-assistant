package com.xently.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class XentlyViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var provider: Provider<out ViewModel>? = creators[modelClass]
        if (provider == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    provider = value
                    break
                }
            }
        }

        if (provider == null) throw IllegalArgumentException("Unknown model class: $modelClass")

        try {
            @Suppress("UNCHECKED_CAST")
            return provider.get() as T
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }
}
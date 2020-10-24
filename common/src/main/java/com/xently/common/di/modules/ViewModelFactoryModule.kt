package com.xently.common.di.modules

import androidx.lifecycle.ViewModelProvider
import com.xently.common.XentlyViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@Module
/**
 * targeted for use within feature modules that doesn't currently support Hilt for DI
 */
@DisableInstallInCheck
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: XentlyViewModelFactory) : ViewModelProvider.Factory
}
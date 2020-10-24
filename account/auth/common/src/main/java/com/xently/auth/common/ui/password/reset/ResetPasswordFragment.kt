package com.xently.auth.common.ui.password.reset

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.xently.auth.common.di.DaggerAuthenticationComponent
import com.xently.auth.common.ui.password.PasswordFragment
import com.xently.auth.common.ui.password.PasswordFragmentConfig
import com.xently.auth.common.ui.password.PasswordViewModel
import com.xently.shoppingassistant.di.AuthenticationModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class ResetPasswordFragment : PasswordFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: ResetPasswordViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = EntryPointAccessors.fromApplication(context.applicationContext,
            AuthenticationModuleDependencies::class.java)
        DaggerAuthenticationComponent.builder().appDependencies(dependencies)
            .build().passwordComponentFactory().create().inject(this)
    }

    override fun getConfig(context: Context) = PasswordFragmentConfig(context)
}
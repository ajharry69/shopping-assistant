package com.xently.auth.common.ui.password.change

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.xently.auth.common.di.DaggerAuthenticationComponent
import com.xently.auth.common.ui.password.PasswordFragment
import com.xently.auth.common.ui.password.PasswordFragmentConfig
import com.xently.shoppingassistant.di.AuthenticationModuleDependencies
import com.xently.user.common.R
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class ChangePasswordFragment : PasswordFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: ChangePasswordViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = EntryPointAccessors.fromApplication(context.applicationContext,
            AuthenticationModuleDependencies::class.java)
        DaggerAuthenticationComponent.builder().appDependencies(dependencies)
            .build().passwordComponentFactory().create().inject(this)
    }

    override fun getConfig(context: Context) = PasswordFragmentConfig(context,
        R.string.change_password,
        R.string.old_password,
        submitButtonLabel = R.string.change_password)
}
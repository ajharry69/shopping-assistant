package com.xently.auth.common.ui.password.reset.request

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.xently.auth.common.R
import com.xently.auth.common.databinding.ResetPasswordRequestFragmentBinding
import com.xently.auth.common.di.DaggerAuthenticationComponent
import com.xently.shoppingassistant.di.AuthenticationModuleDependencies
import com.xently.utilities.ui.fragments.Fragment
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class ResetPasswordRequestFragment : Fragment(R.layout.reset_password_request_fragment) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ResetPasswordRequestViewModel by viewModels { viewModelFactory }
    private var _binding: ResetPasswordRequestFragmentBinding? = null
    private val binding: ResetPasswordRequestFragmentBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = ResetPasswordRequestFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = EntryPointAccessors.fromApplication(context.applicationContext,
            AuthenticationModuleDependencies::class.java)
        DaggerAuthenticationComponent.builder().appDependencies(dependencies)
            .build().passwordComponentFactory().create().inject(this)
    }
}
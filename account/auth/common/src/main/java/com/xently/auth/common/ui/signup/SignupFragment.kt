package com.xently.auth.common.ui.signup

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.xently.auth.common.R
import com.xently.auth.common.databinding.SignupFragmentBinding
import com.xently.auth.common.di.DaggerAuthenticationComponent
import com.xently.shoppingassistant.di.AuthenticationModuleDependencies
import com.xently.utilities.ui.fragments.Fragment
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class SignupFragment : Fragment(R.layout.signup_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SignupViewModel by viewModels { viewModelFactory }
    private var _binding: SignupFragmentBinding? = null
    private val binding: SignupFragmentBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SignupFragmentBinding.bind(view)
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
            .build().signupComponentFactory().create().inject(this)
    }
}
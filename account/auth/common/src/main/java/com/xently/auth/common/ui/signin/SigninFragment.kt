package com.xently.auth.common.ui.signin

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.xently.auth.common.R
import com.xently.auth.common.databinding.SigninFragmentBinding
import com.xently.auth.common.di.DaggerAuthenticationComponent
import com.xently.shoppingassistant.di.AuthenticationModuleDependencies
import com.xently.utilities.ui.fragments.Fragment
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class SigninFragment : Fragment(R.layout.signin_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SigninViewModel by viewModels { viewModelFactory }
    private var _binding: SigninFragmentBinding? = null
    private val binding: SigninFragmentBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = SigninFragmentBinding.bind(view)
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
            .build().signinComponentFactory().create().inject(this)
    }

}
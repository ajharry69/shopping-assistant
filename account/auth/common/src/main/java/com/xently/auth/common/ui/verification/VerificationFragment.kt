package com.xently.auth.common.ui.verification

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.xently.auth.common.R
import com.xently.auth.common.databinding.VerificationFragmentBinding
import com.xently.auth.common.di.DaggerAuthenticationComponent
import com.xently.shoppingassistant.di.AuthenticationModuleDependencies
import com.xently.utilities.ui.fragments.Fragment
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class VerificationFragment : Fragment(R.layout.verification_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: VerificationViewModel by viewModels { viewModelFactory }
    private var _binding: VerificationFragmentBinding? = null
    private val binding: VerificationFragmentBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = VerificationFragmentBinding.bind(view)
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
            .build().verificationComponentFactory().create().inject(this)
    }
}
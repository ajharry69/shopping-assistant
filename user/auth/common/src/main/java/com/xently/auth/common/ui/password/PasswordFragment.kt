package com.xently.auth.common.ui.password

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.xently.auth.common.R
import com.xently.auth.common.databinding.PasswordFragmentBinding
import com.xently.utilities.ui.fragments.Fragment

abstract class PasswordFragment : Fragment(R.layout.password_fragment) {
    abstract fun getConfig(context: Context): PasswordFragmentConfig
    protected open val viewModel: PasswordViewModel by viewModels()
    private var _binding: PasswordFragmentBinding? = null
    protected val binding: PasswordFragmentBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = PasswordFragmentBinding.bind(view).apply {
            config = getConfig(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
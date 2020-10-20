package com.xently.auth.common.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.xently.auth.common.R
import com.xently.auth.common.databinding.SignupFragmentBinding
import com.xently.utilities.ui.fragments.Fragment

class SignupFragment : Fragment(R.layout.signup_fragment) {

    private val viewModel: SignupViewModel by viewModels()
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
}
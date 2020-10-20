package com.xently.auth.common.ui.password.reset.request

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.xently.auth.common.R
import com.xently.auth.common.databinding.ResetPasswordRequestFragmentBinding
import com.xently.utilities.ui.fragments.Fragment

class ResetPasswordRequestFragment : Fragment(R.layout.reset_password_request_fragment) {
    private val viewModel: ResetPasswordRequestViewModel by viewModels()
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
}
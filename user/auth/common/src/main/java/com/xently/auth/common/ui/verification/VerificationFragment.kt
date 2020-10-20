package com.xently.auth.common.ui.verification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.xently.auth.common.R
import com.xently.auth.common.databinding.VerificationFragmentBinding
import com.xently.utilities.ui.fragments.Fragment

class VerificationFragment : Fragment(R.layout.verification_fragment) {

    private val viewModel: VerificationViewModel by viewModels()
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
}
package com.xently.auth.common.ui.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.xently.auth.common.R
import com.xently.auth.common.databinding.SigninFragmentBinding
import com.xently.utilities.ui.fragments.Fragment

class SigninFragment : Fragment(R.layout.signin_fragment) {

    private val viewModel: SigninViewModel by viewModels()
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

}
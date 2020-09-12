package com.xently.auth.provider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xently.auth.provider.databinding.AuthProviderFragmentBinding
import com.xently.utilities.ui.fragments.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthProviderFragment : Fragment() {

    private var _binding: AuthProviderFragmentBinding? = null
    private val binding: AuthProviderFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = AuthProviderFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
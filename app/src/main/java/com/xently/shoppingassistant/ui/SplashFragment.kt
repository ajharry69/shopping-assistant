package com.xently.shoppingassistant.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xently.shoppingassistant.R
import com.xently.shoppingassistant.databinding.FragmentSplashBinding
import com.xently.utilities.ui.fragments.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = FragmentSplashBinding.inflate(inflater, container, false).root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.navDestination.collectLatest {
                findNavController().navigate(it, null, navOptions {
                    launchSingleTop = true
                    popUpTo(R.id.dest_splash) {
                        inclusive = true
                    }
                })
            }
        }
    }
}
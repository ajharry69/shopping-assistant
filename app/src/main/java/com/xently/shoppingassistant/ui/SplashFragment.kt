package com.xently.shoppingassistant.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xently.shoppingassistant.R
import com.xently.utilities.ui.fragments.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
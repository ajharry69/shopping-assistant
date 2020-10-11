package com.xently.auth.provider

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.xently.auth.provider.databinding.AuthProviderFragmentBinding
import com.xently.common.data.TaskResult
import com.xently.common.data.errorMessage
import com.xently.common.utils.Log
import com.xently.models.User
import com.xently.utilities.ui.fragments.Fragment
import com.xently.utilities.viewext.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthProviderFragment : Fragment() {

    private val viewModel: AuthProviderViewModel by viewModels()
    private var _binding: AuthProviderFragmentBinding? = null
    private val binding: AuthProviderFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = AuthProviderFragmentBinding.inflate(inflater, container, false).apply {
            setupToolbar(toolbar)
            google.setStyle(SignInButton.SIZE_WIDE, SignInButton.COLOR_AUTO)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                .requestIdToken(BuildConfig.GOOGLE_AUTH_SERVER_CLIENT_ID).build()
        val client = GoogleSignIn.getClient(view.context, signInOptions)
        binding.email.setClickListener {
            // TODO: Navigate to (email & password) sign-in screen
            showSnackBar("Navigate to (email & password) sign-in screen")
        }
        binding.google.setClickListener {
            startActivityForResult(client.signInIntent, REQUEST_CODE_GOOGLE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_GOOGLE_SIGN_IN) {
            try {
                GoogleSignIn.getSignedInAccountFromIntent(data)?.result?.also {
                    lifecycleScope.launch {
                        viewModel.signIn(it).collectLatest {
                            it.process()
                        }
                    }
                }
            } catch (ex: ApiException) {
                showSnackBar(ex.localizedMessage, Snackbar.LENGTH_LONG)
                Log.show(TAG, ex, ex, Log.Type.ERROR)
            }
        } else super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.dest_home -> {
            lifecycleScope.launch {
                viewModel.skipAuthentication(true).collectLatest {
                    if (it) {
                        // TODO: Navigate to home
                    }
                }
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun TaskResult<User>.process() {
        binding.run {
            when (this@process) {
                is TaskResult.Success -> {
                    if (data.isVerified) {
                        // TODO: Navigate to home screen
                    } else {
                        // TODO: Navigate to verification screen
                    }
                    progressBar.hideThenEnableViews(google, email)
                    hideViews(binding.progressBar)
                }
                is TaskResult.Error -> {
                    progressBar.hideThenEnableViews(google, email)
                    showSnackBar(errorMessage, Snackbar.LENGTH_LONG)
                }
                is TaskResult.Loading -> progressBar.showThenDisableViews(google, email)
            }
        }
    }

    private companion object {
        const val REQUEST_CODE_GOOGLE_SIGN_IN = 123
    }
}
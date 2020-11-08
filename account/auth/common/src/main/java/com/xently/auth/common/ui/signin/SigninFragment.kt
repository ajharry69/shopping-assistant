package com.xently.auth.common.ui.signin

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.xently.auth.common.R
import com.xently.auth.common.databinding.SigninFragmentBinding
import com.xently.auth.common.di.DaggerAuthenticationComponent
import com.xently.common.data.TaskResult
import com.xently.common.data.errorMessage
import com.xently.models.user.User
import com.xently.models.user.UserWithPassword
import com.xently.shoppingassistant.di.AuthenticationModuleDependencies
import com.xently.utilities.ui.fragments.Fragment
import com.xently.utilities.viewext.*
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SigninFragment : Fragment(R.layout.signin_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SigninViewModel by viewModels { viewModelFactory }
    private var _binding: SigninFragmentBinding? = null
    private val binding: SigninFragmentBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = SigninFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            signIn.setClickListener {
                val (username, password) = Pair(username.asString(), password.asString())
                if (username.isNullOrBlank()) {
                    usernameContainer.setErrorText(com.xently.common.R.string.required_field)
                    return@setClickListener
                }
                if (password.isNullOrBlank()) {
                    usernameContainer.setErrorText(com.xently.common.R.string.required_field)
                    return@setClickListener
                }
                lifecycleScope.launch {
                    viewModel.signIn(username, password).collectLatest { result ->
                        when (result) {
                            is TaskResult.Success -> progressBar.hideThenEnableViews(signIn, troubleSigningIn)
                            is TaskResult.Error -> {
                                progressBar.hideThenEnableViews(signIn, troubleSigningIn)
                                showSnackBar(result.errorMessage, Snackbar.LENGTH_LONG)
                            }
                            TaskResult -> progressBar.showThenDisableViews(signIn, troubleSigningIn)
                        }
                    }
                }
            }
            troubleSigningIn.setClickListener {
                val (_, email) = getUsernameAndEmail()
                it.findNavController()
                    .navigate(SigninFragmentDirections.actionRequestPasswordReset(email))
            }
        }
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
            .build().signinComponentFactory().create().inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dest_sign_up -> {
                binding.run {
                    val (username, email) = getUsernameAndEmail()
                    val user = UserWithPassword(User(username = username, email = email),
                        password = password.asString() ?: "")
                    findNavController().navigate(SigninFragmentDirections.actionSignUp(user))
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getUsernameAndEmail(): Pair<String, String?> {
        var email: String? = null
        var username = binding.username.asString() ?: ""
        if (Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            email = username
            username = ""
        }
        return Pair(username, email)
    }
}
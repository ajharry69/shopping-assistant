package com.xently.auth.common.ui.password.change

import android.content.Context
import com.xently.user.common.R
import com.xently.auth.common.ui.password.PasswordFragment
import com.xently.auth.common.ui.password.PasswordFragmentConfig

class ChangePasswordFragment : PasswordFragment() {
    override fun getConfig(context: Context) = PasswordFragmentConfig(context,
        R.string.change_password,
        R.string.old_password,
        submitButtonLabel = R.string.change_password)
}
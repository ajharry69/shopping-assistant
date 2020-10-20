package com.xently.auth.common.ui.password.reset

import android.content.Context
import com.xently.auth.common.ui.password.PasswordFragment
import com.xently.auth.common.ui.password.PasswordFragmentConfig

class ResetPasswordFragment : PasswordFragment() {
    override fun getConfig(context: Context) = PasswordFragmentConfig(context)
}
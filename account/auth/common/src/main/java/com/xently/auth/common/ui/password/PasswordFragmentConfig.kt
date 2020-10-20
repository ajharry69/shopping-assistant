package com.xently.auth.common.ui.password

import android.content.Context
import androidx.annotation.StringRes
import com.xently.user.common.R

data class PasswordFragmentConfig(
    val toolbarTitle: String,
    val oldPasswordHint: String,
    val newPasswordHint: String,
    val submitButtonLabel: String,
) {
    constructor(
        context: Context,
        @StringRes toolbarTitle: Int = R.string.reset_password,
        @StringRes oldPasswordHint: Int = R.string.temporary_password,
        @StringRes newPasswordHint: Int = R.string.new_password,
        @StringRes submitButtonLabel: Int = R.string.reset_password,
    ) : this(context.getString(toolbarTitle),
        context.getString(oldPasswordHint),
        context.getString(newPasswordHint),
        context.getString(submitButtonLabel))
}
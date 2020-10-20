package com.xently.auth.common.ui.signin

import android.view.View
import androidx.navigation.findNavController

fun onHavingTroubleSignInClicked(view: View) {
    view.findNavController().navigate(SigninFragmentDirections.actionRequestPasswordReset())
}
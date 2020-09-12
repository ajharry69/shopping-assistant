package com.xently.utilities.databinding

import android.graphics.drawable.Drawable
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide

@BindingAdapter(value = ["android:enabled"])
fun setEnabled(view: View, enabled: Boolean = false) {
    view.isEnabled = enabled
}

@BindingAdapter(value = ["isVisible"])
fun setVisible(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}

@BindingAdapter(value = ["isRefreshing"])
fun setRefreshing(view: SwipeRefreshLayout, isRefreshing: Boolean = false) {
    view.isRefreshing = isRefreshing
}

@BindingAdapter(value = ["imageFromUrl", "placeholder"], requireAll = false)
fun setImageFromUrl(view: ImageView, url: String?, placeholder: Drawable?) {
    if (url == null) {
        view.setImageDrawable(placeholder)
        return
    }
    Glide.with(view.context)
        .load(url)
        .centerCrop()
        .placeholder(placeholder)
        .into(view)
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) {
    with(view) {
        if (description != null) {
            linksClickable = true
            text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            movementMethod = LinkMovementMethod.getInstance()
        } else text = description
    }
}
package com.writi.extension.custom.tab

import android.view.View
import androidx.annotation.DrawableRes

data class TabModel(
    val id: Int,
    val name: String,
    val drawableRes: String? = null,
    val click: (view: View) -> Unit
) {
    var badge: String? = null
    var selected: Boolean = false
}
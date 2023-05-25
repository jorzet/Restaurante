package unam.fca.restaurante.utils

import android.view.View

fun View.setVisible(): View {
    this.visibility = View.VISIBLE
    return this
}

fun View.setGone(): View {
    this.visibility = View.GONE
    return this
}
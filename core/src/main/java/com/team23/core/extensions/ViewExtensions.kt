package com.team23.core.extensions

import android.view.View

fun View.handleVisibility(isDisplayed: Boolean) {
    this.visibility =
        if (isDisplayed) {
            View.VISIBLE
        } else {
            View.GONE
        }
}

fun View.toggle() {
    this.visibility = if (this.visibility != View.VISIBLE) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
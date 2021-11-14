package com.yyusufsefa.movieapp.util

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun textShorting(string: String, character: Int): String {
    return if (string.length > character)
        "${string.subSequence(0, character)}..."
    else
        string
}

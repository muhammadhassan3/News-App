package com.muhammhassan.newsapp.utils

import android.view.View

object Extension {
    fun View.hide(){
        this.visibility = View.GONE
    }

    fun View.show(){
        this.visibility = View.VISIBLE
    }
}
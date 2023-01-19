package com.muhammhassan.newsapp.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.muhammhassan.newsapp.R

object Extension {
    fun View.hide(){
        this.visibility = View.GONE
    }

    fun View.show(){
        this.visibility = View.VISIBLE
    }

    fun ImageView.loadImage(url: String){
        Glide.with(this).load(url).error(R.drawable.baseline_broken_image_24).placeholder(R.drawable.baseline_image_search_24).into(this)
    }

    fun ImageView.loadImage(@DrawableRes id: Int){
        Glide.with(this).load(id).into(this)
    }
}
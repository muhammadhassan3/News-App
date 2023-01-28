package com.muhammhassan.domain.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.muhammhassan.domain.R
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun parseDate(dateString: String): String{
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val date = format.parse(dateString)

        val normalFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale("id","ID"))
        return date?.let { normalFormat.format(it) } ?: "Format error"
    }

    fun ImageView.loadImage(url: String) {
        Glide.with(this).load(url)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .error(R.drawable.baseline_broken_image_24)
            .placeholder(R.drawable.baseline_image_search_24).into(this)
    }

    fun ImageView.loadImage(@DrawableRes id: Int) {
        Glide.with(this).load(id)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .error(R.drawable.baseline_broken_image_24).into(this)
    }
}
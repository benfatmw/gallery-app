package com.lin.mobile.gallery.ui.model

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * Representation for a [PhotoViewModel] to be displayed
 */
data class PhotoViewModel(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) {
    companion object {
        val TARGET_WIDTH = 400
        val TARGET_HEIGHT = 400

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String) {
            Picasso.get()
                .load(url)
                .resize(
                    TARGET_WIDTH,
                    TARGET_HEIGHT
                ) // @TODO Find a cleaner way to handle images sizes while resizing
                .centerCrop()
                .into(view)
        }
    }
}
package com.lin.mobile.gallery.presentation.model

/**
 * Representation for a [PhotoView] instance for this layer model representation
 */
data class PhotoView(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
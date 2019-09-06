package com.lin.mobile.gallery.data.model


/**
 * Representation for a [AlbumData] fetched from an external layer data source
 */
data class PhotoData(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

package com.lin.mobile.gallery.data.model

/**
 * Representation for a [AlbumData] fetched from an external layer data source
 */
data class AlbumData(
    val userId: Int,
    val id: Int,
    val title: String
)
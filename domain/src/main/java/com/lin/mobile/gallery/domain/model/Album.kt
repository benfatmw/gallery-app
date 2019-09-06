package com.lin.mobile.gallery.domain.model

/**
 * Representation for a [Album] fetched from an external layer data source
 */
data class Album(
    val userId: Int,
    val id: Int,
    val title: String
)
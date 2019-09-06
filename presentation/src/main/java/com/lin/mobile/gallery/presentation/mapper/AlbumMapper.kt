package com.lin.mobile.gallery.presentation.mapper

import com.lin.mobile.gallery.domain.model.Album
import com.lin.mobile.gallery.presentation.model.AlbumView
import javax.inject.Inject

/**
 * Map a [AlbumView] from a [Album] instance when data is moving between
 * this layer and the Domain layer
 */
open class AlbumMapper @Inject constructor() : Mapper<Album, AlbumView>() {
    /**
     * Map a [Album] instance to a [AlbumView] instance
     */
    override fun mapToView(type: Album): AlbumView {
        return AlbumView(
            userId = type.userId,
            id = type.id,
            title = type.title
        )
    }
}
package com.lin.mobile.gallery.presentation.mapper

import com.lin.mobile.gallery.domain.model.Photo
import com.lin.mobile.gallery.presentation.model.PhotoView
import javax.inject.Inject

/**
 * Map a [PhotoView] from a [Photo] instance when data is moving between
 * this layer and the Domain layer
 */
open class PhotoMapper @Inject constructor() : Mapper<Photo, PhotoView>() {
    /**
     * Map a [Photo] instance to a [PhotoView] instance
     */
    override fun mapToView(type: Photo): PhotoView {
        return PhotoView(
            albumId = type.albumId,
            id = type.id,
            title = type.title,
            url = type.url,
            thumbnailUrl = type.thumbnailUrl
        )
    }
}
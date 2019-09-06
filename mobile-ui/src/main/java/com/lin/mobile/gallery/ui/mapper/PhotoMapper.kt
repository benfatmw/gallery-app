package com.lin.mobile.gallery.ui.mapper

import com.lin.mobile.gallery.presentation.model.PhotoView
import com.lin.mobile.gallery.ui.model.PhotoViewModel
import javax.inject.Inject

/**
 * Map a [AlbumView] to and from a [AlbumViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class PhotoMapper @Inject constructor() : Mapper<PhotoViewModel, PhotoView> {
    fun Double.formatDigits(digits: Int) = java.lang.String.format("%.${digits}f", this)

    /**
     * Map a [AlbumView] instance to a [AlbumViewModel] instance
     */
    override fun mapToViewModel(type: PhotoView): PhotoViewModel {
        return PhotoViewModel(albumId = type.albumId,
            id = type.id,
            title = type.title,
            url = type.url,
            thumbnailUrl = type.thumbnailUrl
        )
    }

}
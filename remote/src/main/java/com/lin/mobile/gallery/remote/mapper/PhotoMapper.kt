package com.lin.mobile.gallery.remote.mapper

import com.lin.mobile.gallery.cache.mapper.Mapper
import com.lin.mobile.gallery.data.model.PhotoData
import com.lin.mobile.gallery.remote.model.RemotePhoto
import javax.inject.Inject

/**
 * Map a [PhotoData] to and from a [RemotePhoto] instance when data is moving between
 * this later and the Domain layer
 */
open class PhotoMapper @Inject constructor() : Mapper<RemotePhoto, PhotoData>() {
    override fun mapFromData(type: PhotoData): RemotePhoto {
        return RemotePhoto(
            albumId = type.albumId,
            id = type.id,
            title = type.title,
            url = type.url,
            thumbnailUrl = type.thumbnailUrl
        )
    }

    override fun mapToData(type: RemotePhoto): PhotoData {
        return PhotoData(
            albumId = type.albumId,
            id = type.id,
            title = type.title,
            url = type.url,
            thumbnailUrl = type.thumbnailUrl
        )
    }

}
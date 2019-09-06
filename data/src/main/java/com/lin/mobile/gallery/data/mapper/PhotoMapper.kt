package com.lin.mobile.gallery.data.mapper

import com.lin.mobile.gallery.data.model.PhotoData
import com.lin.mobile.gallery.domain.model.Photo
import javax.inject.Inject

open class PhotoMapper @Inject constructor() : Mapper<Photo, PhotoData>() {

    /**
     * Map a [AlbumData] instance to a [Album] instance
     */
    override fun mapToEntity(type: PhotoData): Photo {
        return Photo(
            albumId = type.albumId,
            id = type.id,
            title = type.title,
            url = type.url,
            thumbnailUrl = type.thumbnailUrl
        )
    }

    /**
     * Map a [Album] instance to a [AlbumData] instance
     */
    override fun mapFromEntity(type: Photo): PhotoData {
        return PhotoData(
            albumId = type.albumId,
            id = type.id,
            title = type.title,
            url = type.url,
            thumbnailUrl = type.thumbnailUrl
        )
    }


}
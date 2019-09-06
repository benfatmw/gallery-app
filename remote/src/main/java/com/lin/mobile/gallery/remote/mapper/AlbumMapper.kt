package com.lin.mobile.gallery.remote.mapper

import com.lin.mobile.gallery.cache.mapper.Mapper

import com.lin.mobile.gallery.data.model.AlbumData
import com.lin.mobile.gallery.remote.model.RemoteAlbum
import javax.inject.Inject


/**
 * Map a [AlbumData] to and from a [RemoteAlbum] instance when data is moving between
 * this later and the Domain layer
 */
open class AlbumMapper @Inject constructor() : Mapper<RemoteAlbum, AlbumData>() {
    override fun mapFromData(type: AlbumData): RemoteAlbum {
        return RemoteAlbum(
            userId = type.userId,
            id = type.id,
            title = type.title
        )
    }

    override fun mapToData(type: RemoteAlbum): AlbumData {
        return AlbumData(
            userId = type.userId,
            id = type.id,
            title = type.title
        )
    }

}
package com.lin.mobile.gallery.cache.mapper

import com.lin.mobile.gallery.cache.model.CachedAlbum
import com.lin.mobile.gallery.data.model.AlbumData
import javax.inject.Inject


/**
 * Map a [ProductData] to and from a [Product] instance when data is moving between
 * this later and the Domain layer
 */
open class AlbumMapper @Inject constructor() : PhotMapper<CachedAlbum, AlbumData>() {
    override fun mapFromData(type: AlbumData): CachedAlbum {
        return CachedAlbum(
            userId = type.userId,
            id = type.id,
            title = type.title
        )
    }

    override fun mapToData(type: CachedAlbum): AlbumData {
        return AlbumData(
            userId = type.userId,
            id = type.id,
            title = type.title
        )
    }

}
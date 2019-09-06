package com.lin.mobile.gallery.data.mapper

import com.lin.mobile.gallery.data.model.AlbumData
import com.lin.mobile.gallery.domain.model.Album
import javax.inject.Inject


/**
 * Map a [AlbumData] to and from a [Album] instance when data is moving between
 * this later and the Domain layer
 */
open class AlbumMapper @Inject constructor() : Mapper<Album, AlbumData>() {

    /**
     * Map a [AlbumData] instance to a [Album] instance
     */
    override fun mapToEntity(type: AlbumData): Album {
        return Album(
            userId = type.userId,
            id = type.id,
            title = type.title
        )
    }

    /**
     * Map a [Album] instance to a [AlbumData] instance
     */
    override fun mapFromEntity(type: Album): AlbumData {
        return AlbumData(
            userId = type.userId,
            id = type.id,
            title = type.title
        )
    }


}
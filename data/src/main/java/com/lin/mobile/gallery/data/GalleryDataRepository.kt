package com.lin.mobile.gallery.data

import com.lin.mobile.gallery.data.mapper.AlbumMapper
import com.lin.mobile.gallery.data.mapper.PhotoMapper

import com.lin.mobile.gallery.data.source.GalleryDataStoreFactory
import com.lin.mobile.gallery.domain.model.Album
import com.lin.mobile.gallery.domain.model.Photo
import com.lin.mobile.gallery.domain.repository.GalleryRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Provides an implementation of the [GalleryRepository] interface for communicating to and from
 * data sources
 */
class GalleryDataRepository @Inject constructor(private val factory: GalleryDataStoreFactory,
                                                private val albumMapper: AlbumMapper,
                                                private val photoMapper: PhotoMapper
) :
    GalleryRepository {

    override fun getAlbums(): Flowable<List<Album>> {
        return factory.retrieveDataStore(false).getAlbums()
                .map {
                    albumMapper.mapToEntity(it)
                }
    }

    override fun getPhotos(albumId:Int): Flowable<List<Photo>> {
        return factory.retrieveDataStore(false).getPhotos(albumId)
            .map {
                photoMapper.mapToEntity(it)
            }
    }

}
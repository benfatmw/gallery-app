package com.lin.mobile.gallery.cache

import com.lin.mobile.gallery.cache.db.GalleryDatabase
import com.lin.mobile.gallery.cache.mapper.AlbumMapper
import com.lin.mobile.gallery.data.model.AlbumData
import com.lin.mobile.gallery.data.model.PhotoData
import com.lin.mobile.gallery.data.model.UserData
import com.lin.mobile.gallery.data.repository.GalleryDataStore
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject


/**
 * Cached implementation for retrieving and saving Live objects instances. This class implements the
 * [LiveCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class GalleryCache @Inject constructor( private val database: GalleryDatabase,
                                        private val productMapper: AlbumMapper
) :
    GalleryDataStore {

    override fun isCached(): Single<Boolean> {
        return Single.just(true)
    }

    override fun getAlbums(): Flowable<List<AlbumData>> {
        return database.galleryDao().getAlbums()
            .map { productMapper.mapToData(it) }
    }

    override fun getPhotos(albumId:Int): Flowable<List<PhotoData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsers(userId: Int): Flowable<List<UserData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
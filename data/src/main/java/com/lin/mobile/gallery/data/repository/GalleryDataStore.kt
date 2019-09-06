package com.lin.mobile.gallery.data.repository

import com.lin.mobile.gallery.data.model.AlbumData
import com.lin.mobile.gallery.data.model.PhotoData
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Live programs.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface GalleryDataStore {

    fun isCached(): Single<Boolean>

    fun getAlbums(): Flowable<List<AlbumData>>

    fun getPhotos(albumId:Int): Flowable<List<PhotoData>>
}
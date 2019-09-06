package com.lin.mobile.gallery.domain.repository

import com.lin.mobile.gallery.domain.model.Album
import com.lin.mobile.gallery.domain.model.Photo
import io.reactivex.Flowable

interface GalleryRepository {
    fun getAlbums(): Flowable<List<Album>>
    fun getPhotos(albumId:Int): Flowable<List<Photo>>
}
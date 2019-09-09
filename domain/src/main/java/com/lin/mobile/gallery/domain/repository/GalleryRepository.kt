package com.lin.mobile.gallery.domain.repository

import com.lin.mobile.gallery.domain.model.Album
import com.lin.mobile.gallery.domain.model.Photo
import com.lin.mobile.gallery.domain.model.User
import io.reactivex.Flowable

interface GalleryRepository {
    fun getAlbums(): Flowable<List<Album>>
    fun getPhotos(albumId:Int): Flowable<List<Photo>>
    fun getUser(userId:Int): Flowable<List<User>>
}
package com.lin.mobile.gallery.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lin.mobile.gallery.cache.dao.CachedGalleryDao
import com.lin.mobile.gallery.cache.model.CachedAlbum

@Database(entities = arrayOf(CachedAlbum::class), version = 1)
abstract class GalleryDatabase : RoomDatabase() {

    abstract fun galleryDao(): CachedGalleryDao
}
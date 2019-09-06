package com.lin.mobile.gallery.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.lin.mobile.gallery.cache.db.CacheConstants
import com.lin.mobile.gallery.cache.model.CachedAlbum
import io.reactivex.Flowable


@Dao
abstract class CachedGalleryDao {
    @Query(CacheConstants.QUERY_ALBUMS)
    abstract fun getAlbums(): Flowable<List<CachedAlbum>>
}
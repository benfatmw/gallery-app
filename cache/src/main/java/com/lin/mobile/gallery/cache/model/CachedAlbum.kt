package com.lin.mobile.gallery.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lin.mobile.gallery.cache.db.CacheConstants

/**
 * Model used solely for the caching of a product
 */
@Entity(tableName = CacheConstants.TABLE_NAME_ALBUM)
data class CachedAlbum(
        @PrimaryKey
        val userId: Int,
        val id: Int,
        val title: String

)
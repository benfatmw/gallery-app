package com.lin.mobile.gallery.ui.mapper

import com.lin.mobile.gallery.presentation.model.AlbumView
import com.lin.mobile.gallery.ui.model.AlbumViewModel
import javax.inject.Inject

/**
 * Map a [AlbumView] to and from a [AlbumViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class AlbumMapper @Inject constructor() : Mapper<AlbumViewModel, AlbumView> {
    fun Double.formatDigits(digits: Int) = java.lang.String.format("%.${digits}f", this)

    /**
     * Map a [AlbumView] instance to a [AlbumViewModel] instance
     */
    override fun mapToViewModel(type: AlbumView): AlbumViewModel {
        return AlbumViewModel(userId = type.userId,
            id = type.id,
            title = type.title
        )
    }

}
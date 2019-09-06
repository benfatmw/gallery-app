package com.lin.mobile.gallery.ui.injection.ui

import com.lin.mobile.gallery.domain.interactor.items.GetPhotos
import com.lin.mobile.gallery.presentation.mapper.PhotoMapper
import com.lin.mobile.gallery.presentation.photos.BrowsePhotosViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Module used to provide dependencies at a fragment-level.
 */
@Module
open class BrowsePhotosFragmentModule {

    @Provides
    fun provideBrowseAlbumsViewModelFactory(getAlbums: GetPhotos,
                                            mapper: PhotoMapper
    ):
            BrowsePhotosViewModelFactory {
        return BrowsePhotosViewModelFactory(getAlbums, mapper)
    }

}
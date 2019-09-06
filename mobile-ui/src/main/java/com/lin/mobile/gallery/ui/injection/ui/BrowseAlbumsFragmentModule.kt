package com.lin.mobile.gallery.ui.injection.ui

import com.lin.mobile.gallery.domain.interactor.items.GetAlbums
import com.lin.mobile.gallery.presentation.mapper.AlbumMapper
import com.lin.mobile.gallery.presentation.albums.BrowseAlbumsViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Module used to provide dependencies at a fragment-level.
 */
@Module
open class BrowseAlbumsFragmentModule {

    @Provides
    fun provideBrowseAlbumsViewModelFactory(getAlbums: GetAlbums,
                                            mapper: AlbumMapper
    ):
            BrowseAlbumsViewModelFactory {
        return BrowseAlbumsViewModelFactory(getAlbums, mapper)
    }

}

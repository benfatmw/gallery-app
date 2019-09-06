package com.lin.mobile.gallery.presentation.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lin.mobile.gallery.domain.interactor.items.GetAlbums
import com.lin.mobile.gallery.presentation.mapper.AlbumMapper

open class BrowseAlbumsViewModelFactory(
    private val getAlbums: GetAlbums,
    private val albumMapper: AlbumMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrowseAlbumsViewModel::class.java)) {
            return BrowseAlbumsViewModel(getAlbums, albumMapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
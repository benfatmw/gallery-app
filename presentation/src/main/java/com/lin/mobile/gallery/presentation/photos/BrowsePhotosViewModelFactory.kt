package com.lin.mobile.gallery.presentation.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lin.mobile.gallery.domain.interactor.items.GetPhotos
import com.lin.mobile.gallery.presentation.mapper.PhotoMapper

open class BrowsePhotosViewModelFactory(
    private val getPhotos: GetPhotos,
    private val photoMapper: PhotoMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrowsePhotosViewModel::class.java)) {
            return BrowsePhotosViewModel(getPhotos, photoMapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
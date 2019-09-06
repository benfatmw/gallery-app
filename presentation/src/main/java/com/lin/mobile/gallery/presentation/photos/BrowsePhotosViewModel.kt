package com.lin.mobile.gallery.presentation.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lin.mobile.gallery.domain.interactor.items.GetPhotos
import com.lin.mobile.gallery.domain.model.Photo
import com.lin.mobile.gallery.presentation.Resource
import com.lin.mobile.gallery.presentation.ResourceState
import com.lin.mobile.gallery.presentation.mapper.PhotoMapper
import com.lin.mobile.gallery.presentation.model.PhotoView
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

open class BrowsePhotosViewModel @Inject internal constructor(
    private val getPhotos: GetPhotos,
    private val photoMapper: PhotoMapper
) : ViewModel() {

    private val photosLiveData: MutableLiveData<Resource<List<PhotoView>>> =
        MutableLiveData()

    init {

    }

    override fun onCleared() {
        getPhotos.dispose()
        super.onCleared()
    }

    fun getPhotos(param: Int): LiveData<Resource<List<PhotoView>>> {
        fetchPhotos(param)
        return photosLiveData
    }

    fun fetchPhotos(param:Int) {

        photosLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getPhotos.execute(PhotoSubscriber(),param)
    }

    inner class PhotoSubscriber : DisposableSubscriber<List<Photo>>() {

        override fun onComplete() {}

        override fun onNext(t: List<Photo>) {
            photosLiveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.map { photoMapper.mapToView(it) }, null
                )
            )
        }

        override fun onError(exception: Throwable) {
            photosLiveData.postValue(
                Resource(ResourceState.ERROR, null, exception.message)
            )
        }

    }

}
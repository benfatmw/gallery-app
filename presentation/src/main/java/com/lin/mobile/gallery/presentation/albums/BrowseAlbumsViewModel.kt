package com.lin.mobile.gallery.presentation.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lin.mobile.gallery.domain.interactor.items.GetAlbums
import com.lin.mobile.gallery.domain.model.Album
import com.lin.mobile.gallery.presentation.Resource
import com.lin.mobile.gallery.presentation.ResourceState
import com.lin.mobile.gallery.presentation.mapper.AlbumMapper
import com.lin.mobile.gallery.presentation.model.AlbumView
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject


open class BrowseAlbumsViewModel @Inject internal constructor(
    private val getAlbums: GetAlbums,
    private val albumMapper: AlbumMapper
) : ViewModel() {

    private val albumsLiveData: MutableLiveData<Resource<List<AlbumView>>> =
        MutableLiveData()

    init {
        fetchAlbums()
    }

    override fun onCleared() {
        getAlbums.dispose()
        super.onCleared()
    }

    fun getAlbums(): LiveData<Resource<List<AlbumView>>> {
        return albumsLiveData
    }

    fun fetchAlbums() {
        albumsLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getAlbums.execute(AlbumSubscriber())
    }

    inner class AlbumSubscriber : DisposableSubscriber<List<Album>>() {

        override fun onComplete() {}

        override fun onNext(t: List<Album>) {
            albumsLiveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.map { albumMapper.mapToView(it) }, null
                )
            )
        }

        override fun onError(exception: Throwable) {
            albumsLiveData.postValue(
                Resource(ResourceState.ERROR, null, exception.message)
            )
        }

    }

}
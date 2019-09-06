package com.lin.mobile.gallery.remote

import com.lin.mobile.gallery.data.model.AlbumData
import com.lin.mobile.gallery.data.model.PhotoData
import com.lin.mobile.gallery.data.repository.GalleryDataStore
import com.lin.mobile.gallery.remote.mapper.AlbumMapper
import com.lin.mobile.gallery.remote.mapper.PhotoMapper
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GalleryRemote @Inject constructor(@Inject val api : GetRemoteDataService,
                                        private val albumMapper: AlbumMapper,
                                        private val photoMapper: PhotoMapper

) :
    GalleryDataStore {

    override fun isCached(): Single<Boolean> {
        throw NotImplementedError()
    }

    override fun getAlbums(): Flowable<List<AlbumData>> {

        return api.getAlbums()
            .map { albumMapper.mapToData(it) }

    }

    override fun getPhotos( albumId: Int): Flowable<List<PhotoData>> {

        return api.getPhotosByAlbumId(albumId)
            .map { photoMapper.mapToData(it) }

    }
}
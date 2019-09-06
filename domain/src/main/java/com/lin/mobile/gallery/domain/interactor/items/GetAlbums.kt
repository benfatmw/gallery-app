package com.lin.mobile.gallery.domain.interactor.items

import com.lin.mobile.gallery.domain.executor.PostExecutionThread
import com.lin.mobile.gallery.domain.executor.ThreadExecutor
import com.lin.mobile.gallery.domain.interactor.FlowableUseCase
import com.lin.mobile.gallery.domain.model.Album
import com.lin.mobile.gallery.domain.repository.GalleryRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Use case used for retrieving a [List] of [Album] instances from the [GalleryRepository]
 */
open class GetAlbums @Inject constructor(val repository: GalleryRepository,
                                         threadExecutor: ThreadExecutor,
                                         postExecutionThread: PostExecutionThread
) :
    FlowableUseCase<List<Album>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<List<Album>> {

        return repository.getAlbums()
    }

}
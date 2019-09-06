package com.lin.mobile.gallery.domain.interactor.items

import com.lin.mobile.gallery.domain.executor.PostExecutionThread
import com.lin.mobile.gallery.domain.executor.ThreadExecutor
import com.lin.mobile.gallery.domain.interactor.FlowableUseCase
import com.lin.mobile.gallery.domain.model.Photo
import com.lin.mobile.gallery.domain.repository.GalleryRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Use case used for retrieving a [List] of [Photo] instances from the [GalleryRepository]
 */
open class GetPhotos @Inject constructor(val repository: GalleryRepository,
                                         threadExecutor: ThreadExecutor,
                                         postExecutionThread: PostExecutionThread
) :
    FlowableUseCase<List<Photo>, Int>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Int?): Flowable<List<Photo>> {

        return repository.getPhotos(params!!)
    }

}
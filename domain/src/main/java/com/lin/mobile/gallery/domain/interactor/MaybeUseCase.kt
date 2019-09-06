package com.lin.mobile.gallery.domain.interactor

import com.lin.mobile.gallery.domain.executor.PostExecutionThread
import com.lin.mobile.gallery.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers


/**
 * Abstract class for a UseCase that returns an instance of a [Flowable].
 */
abstract class MaybeUseCase<T, in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    /**
     * Builds a [Maybe] which will be used when the current [MaybeUseCase] is executed.
     */
    protected abstract fun buildUseCaseObservable(params: Params? = null): Maybe<T>

    /**
     * Executes the current use case.
     */
    open fun execute(observer: DisposableMaybeObserver<T>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler) as Maybe<T>
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}
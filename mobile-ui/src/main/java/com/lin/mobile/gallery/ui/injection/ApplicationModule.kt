package com.lin.mobile.gallery.ui.injection

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import com.lin.mobile.gallery.domain.executor.JobExecutor
import com.lin.mobile.gallery.domain.executor.PostExecutionThread
import com.lin.mobile.gallery.domain.executor.ThreadExecutor
import com.lin.mobile.gallery.ui.UiThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    internal fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.NewInstanceFactory()
    }
}
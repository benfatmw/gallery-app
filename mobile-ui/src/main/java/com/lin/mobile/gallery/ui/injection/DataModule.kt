package com.lin.mobile.gallery.ui.injection

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lin.mobile.gallery.cache.GalleryCache
import com.lin.mobile.gallery.cache.db.CacheConstants
import com.lin.mobile.gallery.cache.db.GalleryDatabase
import com.lin.mobile.gallery.data.GalleryDataRepository
import com.lin.mobile.gallery.data.repository.GalleryDataStore
import com.lin.mobile.gallery.data.source.GalleryDataStoreFactory
import com.lin.mobile.gallery.domain.repository.GalleryRepository
import com.lin.mobile.gallery.remote.GalleryRemote
import com.lin.mobile.gallery.remote.GetRemoteDataService
import com.lin.mobile.gallery.remote.mapper.AlbumMapper
import com.lin.mobile.gallery.remote.mapper.PhotoMapper
import com.lin.mobile.gallery.remote.mapper.UserMapper
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class DataModule {


    @Provides
    @Singleton
    internal fun providePosRepository(
        factory: GalleryDataStoreFactory,
        albumMapper: com.lin.mobile.gallery.data.mapper.AlbumMapper,
        photoMapper: com.lin.mobile.gallery.data.mapper.PhotoMapper,
        userMapper: com.lin.mobile.gallery.data.mapper.UserMapper
    ): GalleryRepository {
        return GalleryDataRepository(factory, albumMapper,photoMapper,userMapper)
    }

    @Provides
    @Singleton
    @Named("cache")
    internal fun provideCacheGalleryDataStore(database: GalleryDatabase,
                                              mapper: com.lin.mobile.gallery.cache.mapper.AlbumMapper
    ): GalleryDataStore {
        return GalleryCache(database,mapper)
    }

    @Provides
    @Singleton
    @Named("remote")
    internal fun provideRemoteGalleryDataStore(api : GetRemoteDataService,
                                               albumMapper: AlbumMapper,
                                               photoMapper: PhotoMapper,
                                               userMapper: UserMapper
    ): GalleryDataStore {
        return GalleryRemote(api,albumMapper,photoMapper,userMapper)
    }

    @Singleton
    @Provides
    internal fun provideRemoteDataService(): GetRemoteDataService {
        return Retrofit.Builder()
            .baseUrl(GetRemoteDataService.HTTPS_API_ALBUMS_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetRemoteDataService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideCacheDatabase(application: Application): GalleryDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            GalleryDatabase::class.java, "gallery.db"
        )
            .addCallback(
                object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // moving to a new thread
                        db.execSQL(CacheConstants.POPULATE_DATA)
                    }
                }
            )
            .build()

    }
}
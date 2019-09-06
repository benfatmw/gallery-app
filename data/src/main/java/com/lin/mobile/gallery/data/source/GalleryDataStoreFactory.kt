package com.lin.mobile.gallery.data.source

import com.lin.mobile.gallery.data.repository.GalleryDataStore
import javax.inject.Inject
import javax.inject.Named

/**
 * Create an instance of a GalleryDataStore
 */
open class GalleryDataStoreFactory @Inject constructor(
    @Named("cache") private val cacheDataStore: GalleryDataStore,
    @Named("remote") private val remoteDataStore: GalleryDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache
     */
    open fun retrieveDataStore(isCached: Boolean): GalleryDataStore {
        if (isCached ) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): GalleryDataStore {
        return cacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): GalleryDataStore {
        return remoteDataStore
    }

}
package com.lin.mobile.gallery.ui.injection

import com.lin.mobile.gallery.ui.MainActivity
import com.lin.mobile.gallery.ui.injection.ui.BrowseAlbumsFragmentModule
import com.lin.mobile.gallery.ui.albums.BrowseAlbumsFragment
import com.lin.mobile.gallery.ui.injection.ui.BrowsePhotosFragmentModule
import com.lin.mobile.gallery.ui.photos.BrowsePhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersBindingModule {
    @ContributesAndroidInjector(modules = [])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(BrowseAlbumsFragmentModule::class)])
    abstract fun bindBrowseAlbumsFragment(): BrowseAlbumsFragment

    @ContributesAndroidInjector(modules = [(BrowsePhotosFragmentModule::class)])
    abstract fun bindBrowsePhotosFragment(): BrowsePhotosFragment

}
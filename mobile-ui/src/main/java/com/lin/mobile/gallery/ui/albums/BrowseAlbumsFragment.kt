package com.lin.mobile.gallery.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.lin.mobile.gallery.R
import com.lin.mobile.gallery.presentation.Resource
import com.lin.mobile.gallery.presentation.ResourceState
import com.lin.mobile.gallery.presentation.model.AlbumView
import com.lin.mobile.gallery.presentation.albums.BrowseAlbumsViewModel
import com.lin.mobile.gallery.presentation.albums.BrowseAlbumsViewModelFactory
import com.lin.mobile.gallery.ui.mapper.AlbumMapper
import com.lin.mobile.gallery.ui.model.AlbumViewModel
import com.lin.mobile.gallery.ui.widget.empty.EmptyListener
import com.lin.mobile.gallery.ui.widget.error.ErrorListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_browse_albums.*
import javax.inject.Inject




class BrowseAlbumsFragment : androidx.fragment.app.Fragment() {
    @Inject
    lateinit var albumsAdapter: BrowseAlbumsAdapter
    @Inject
    lateinit var mapper: AlbumMapper
    @Inject
    lateinit var viewModelFactory: BrowseAlbumsViewModelFactory
    private lateinit var browseAlbumsViewModel: BrowseAlbumsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection
            .inject(this)

        browseAlbumsViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BrowseAlbumsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_browse_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBrowseRecycler()
        setupViewListeners()
    }

    override fun onStart() {
        super.onStart()
        browseAlbumsViewModel.getAlbums().observe(this,
            Observer<Resource<List<AlbumView>>> {
                if (it != null) this.handleDataState(it.status, it.data, it.message)
            })
    }

    private fun setupBrowseRecycler() {
        albumsAdapter.clickListener = { albums: AlbumViewModel -> itemAlbumClicked(albums) }
        recycler_browse.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this.context, 1)
        recycler_browse.adapter = albumsAdapter
    }

    private fun itemAlbumClicked(album: AlbumViewModel) {
        val args = Bundle()
        args.putInt("albumId", album.id)
        NavHostFragment.findNavController(nav_host_fragment).navigate(R.id.browsePhotosFragment, args)
    }

    private fun handleDataState(
        resourceState: ResourceState, data: List<AlbumView>?,
        message: String?
    ) {
        when (resourceState) {
            ResourceState.LOADING -> setupScreenForLoadingState()
            ResourceState.SUCCESS -> setupScreenForSuccess(data)
            ResourceState.ERROR -> setupScreenForError(message)
        }
    }

    private fun setupScreenForLoadingState() {
        progress.visibility = View.VISIBLE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.GONE
    }

    private fun setupScreenForSuccess(data: List<AlbumView>?) {
        view_error.visibility = View.GONE
        progress.visibility = View.GONE
        if (data != null && data.isNotEmpty()) {
            updateListView(data)
            recycler_browse.visibility = View.VISIBLE
        } else {
            view_empty.visibility = View.VISIBLE
        }
    }

    private fun updateListView(albums: List<AlbumView>) {
        albumsAdapter.albums = albums.sortedBy {it.title}.map { mapper.mapToViewModel(it) }
        albumsAdapter.notifyDataSetChanged()
    }

    private fun setupScreenForError(message: String?) {
        progress.visibility = View.GONE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.VISIBLE
    }

    private fun setupViewListeners() {
        view_empty.emptyListener = emptyListener
        view_error.errorListener = errorListener
    }

    private val emptyListener = object : EmptyListener {
        override fun onCheckAgainClicked() {
            browseAlbumsViewModel.fetchAlbums()
        }
    }

    private val errorListener = object : ErrorListener {
        override fun onTryAgainClicked() {
            browseAlbumsViewModel.fetchAlbums()
        }
    }

}
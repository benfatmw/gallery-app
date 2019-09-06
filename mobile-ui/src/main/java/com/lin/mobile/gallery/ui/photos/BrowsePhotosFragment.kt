package com.lin.mobile.gallery.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lin.mobile.gallery.R
import com.lin.mobile.gallery.presentation.Resource
import com.lin.mobile.gallery.presentation.ResourceState
import com.lin.mobile.gallery.presentation.model.PhotoView
import com.lin.mobile.gallery.presentation.photos.BrowsePhotosViewModel
import com.lin.mobile.gallery.presentation.photos.BrowsePhotosViewModelFactory
import com.lin.mobile.gallery.ui.mapper.PhotoMapper
import com.lin.mobile.gallery.ui.model.PhotoViewModel
import com.lin.mobile.gallery.ui.widget.empty.EmptyListener
import com.lin.mobile.gallery.ui.widget.error.ErrorListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_browse_photos.*
import javax.inject.Inject


class BrowsePhotosFragment : androidx.fragment.app.Fragment() {
    @Inject
    lateinit var photoAdapter: BrowsePhotosAdapter
    @Inject
    lateinit var mapper: PhotoMapper
    @Inject
    lateinit var viewModelFactory: BrowsePhotosViewModelFactory
    private lateinit var browsePhotosViewModel: BrowsePhotosViewModel

    var albumId:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection
            .inject(this)

        browsePhotosViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BrowsePhotosViewModel::class.java)

        return inflater.inflate(R.layout.fragment_browse_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBrowseRecycler()
        setupViewListeners()
    }

    override fun onStart() {
        super.onStart()
        albumId = this.getArguments()!!.getInt("albumId")
        browsePhotosViewModel.getPhotos(albumId).observe(this,
            Observer<Resource<List<PhotoView>>> {
                if (it != null) this.handleDataState(it.status, it.data, it.message)
            })
    }

    private fun setupBrowseRecycler() {
        photoAdapter.clickListener = { photo: PhotoViewModel -> itemPhotoClicked(photo) }
        recycler_browse.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this.context,2)
        recycler_browse.adapter = photoAdapter
    }

    private fun itemPhotoClicked(photo:PhotoViewModel) {
    }

    private fun handleDataState(
        resourceState: ResourceState, data: List<PhotoView>?,
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

    private fun setupScreenForSuccess(data: List<PhotoView>?) {
        view_error.visibility = View.GONE
        progress.visibility = View.GONE
        if (data != null && data.isNotEmpty()) {
            updateListView(data)
            recycler_browse.visibility = View.VISIBLE
        } else {
            view_empty.visibility = View.VISIBLE
        }
    }

    private fun updateListView(photos: List<PhotoView>) {
        photoAdapter.photos = photos.map { mapper.mapToViewModel(it) }
        photoAdapter.notifyDataSetChanged()
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
            browsePhotosViewModel.fetchPhotos(albumId)
        }
    }

    private val errorListener = object : ErrorListener {
        override fun onTryAgainClicked() {
            browsePhotosViewModel.fetchPhotos(albumId)
        }
    }

}
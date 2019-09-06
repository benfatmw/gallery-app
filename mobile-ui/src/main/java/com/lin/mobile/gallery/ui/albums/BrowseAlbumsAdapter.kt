package com.lin.mobile.gallery.ui.albums

import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lin.mobile.gallery.R
import javax.inject.Inject
import com.lin.mobile.gallery.databinding.ItemAlbumBinding
import com.lin.mobile.gallery.ui.model.AlbumViewModel

class BrowseAlbumsAdapter @Inject constructor() : androidx.recyclerview.widget.RecyclerView.Adapter<BrowseAlbumsAdapter.ViewHolder>() {

    var albums: List<AlbumViewModel> = arrayListOf()
    lateinit var clickListener: (AlbumViewModel) -> Unit


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albums[position], clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = DataBindingUtil.inflate<ItemAlbumBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_album, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    inner class ViewHolder(var binding: ItemAlbumBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: AlbumViewModel, clickListener: (AlbumViewModel) -> Unit) {
            binding.album = viewModel
            itemView.setOnClickListener { clickListener(viewModel) }
        }
    }

}
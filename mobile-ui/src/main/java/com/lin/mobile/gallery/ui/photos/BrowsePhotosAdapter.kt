package com.lin.mobile.gallery.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.lin.mobile.gallery.R
import com.lin.mobile.gallery.databinding.ItemPhotoBinding
import com.lin.mobile.gallery.ui.model.PhotoViewModel
import javax.inject.Inject

class BrowsePhotosAdapter @Inject constructor() : androidx.recyclerview.widget.RecyclerView.Adapter<BrowsePhotosAdapter.ViewHolder>() {

    var photos: List<PhotoViewModel> = arrayListOf()
    lateinit var clickListener: (PhotoViewModel) -> Unit


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position], clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = DataBindingUtil.inflate<com.lin.mobile.gallery.databinding.ItemPhotoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_photo, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class ViewHolder(var binding: ItemPhotoBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: PhotoViewModel, clickListener: (PhotoViewModel) -> Unit) {
            binding.photo = viewModel
            itemView.setOnClickListener { clickListener(viewModel) }
        }
    }

}
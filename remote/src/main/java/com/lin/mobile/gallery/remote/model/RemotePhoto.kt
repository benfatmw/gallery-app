package com.lin.mobile.gallery.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePhoto(@field:SerializedName("albumId") val albumId: Int,
                       @field:SerializedName("id") var id: Int,
                       @field:SerializedName("title") var title: String,
                       @field:SerializedName("url") var url: String,
                       @field:SerializedName("thumbnailUrl") var thumbnailUrl: String)
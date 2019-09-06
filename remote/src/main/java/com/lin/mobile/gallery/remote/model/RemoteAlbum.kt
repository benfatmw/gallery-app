package com.lin.mobile.gallery.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteAlbum(@field:SerializedName("userId") val userId: Int,
                       @field:SerializedName("id") var id: Int,
                       @field:SerializedName("title") var title: String)
package com.lin.mobile.gallery.remote.model


import com.google.gson.annotations.SerializedName

data class RemoteUser(@field:SerializedName("id")val id:Int,
                      @field:SerializedName("name")var name:String,
                      @field:SerializedName("username")var username:String)
package com.lin.mobile.gallery.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lin.mobile.gallery.domain.interactor.items.GetUsers
import com.lin.mobile.gallery.presentation.mapper.UserMapper

open class BrowseUsersViewModelFactory(
    private val getUsers: GetUsers,
    private val userMapper: UserMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrowseUsersViewModel::class.java)) {
            return BrowseUsersViewModel(getUsers, userMapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
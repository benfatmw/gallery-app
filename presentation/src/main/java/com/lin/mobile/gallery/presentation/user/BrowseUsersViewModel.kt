package com.lin.mobile.gallery.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lin.mobile.gallery.domain.interactor.items.GetUsers
import com.lin.mobile.gallery.domain.model.User
import com.lin.mobile.gallery.presentation.Resource
import com.lin.mobile.gallery.presentation.ResourceState
import com.lin.mobile.gallery.presentation.mapper.UserMapper
import com.lin.mobile.gallery.presentation.model.UserView
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

open class BrowseUsersViewModel @Inject internal constructor(
    private val getUsers: GetUsers,
    private val userMapper: UserMapper
) : ViewModel() {

    private val usersLiveData: MutableLiveData<Resource<List<UserView>>> =
        MutableLiveData()

    init {

    }

    override fun onCleared() {
        getUsers.dispose()
        super.onCleared()
    }

    fun getUsers(param: Int): LiveData<Resource<List<UserView>>> {
        fetchUsers(param)
        return usersLiveData
    }

    fun fetchUsers(param:Int) {

        usersLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getUsers.execute(UserSubscriber(),param)
    }

    inner class UserSubscriber : DisposableSubscriber<List<User>>() {

        override fun onComplete() {}

        override fun onNext(t: List<User>) {
            usersLiveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.map { userMapper.mapToView(it) }, null
                )
            )
        }

        override fun onError(exception: Throwable) {
            usersLiveData.postValue(
                Resource(ResourceState.ERROR, null, exception.message)
            )
        }

    }

}
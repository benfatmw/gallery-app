package com.lin.mobile.gallery.remote.mapper

import com.lin.mobile.gallery.cache.mapper.Mapper
import com.lin.mobile.gallery.data.model.UserData
import com.lin.mobile.gallery.remote.model.RemoteUser
import javax.inject.Inject

/**
 * Map a [UserData] to and from a [RemoteUser] instance when data is moving between
 * this later and the Domain layer
 */

open class UserMapper @Inject constructor() : Mapper<RemoteUser, UserData>() {
    override fun mapFromData(type: UserData): RemoteUser {
        return RemoteUser(
            id = type.id,
            name = type.name,
            username = type.username
        )
    }

    override fun mapToData(type: RemoteUser): UserData {
        return UserData(
            id = type.id,
            name = type.name,
            username = type.username
        )
    }

}
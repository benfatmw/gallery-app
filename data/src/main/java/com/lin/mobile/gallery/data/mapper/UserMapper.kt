package com.lin.mobile.gallery.data.mapper

import com.lin.mobile.gallery.data.model.UserData
import com.lin.mobile.gallery.domain.model.User
import javax.inject.Inject

open class UserMapper @Inject constructor() : Mapper<User, UserData>() {

    /**
     * Map a [UserData] instance to a [User] instance
     */
    override fun mapToEntity(type: UserData): User {
        return User(
            id = type.id,
            name = type.name,
            username = type.username
        )
    }

    /**
     * Map a [Album] instance to a [AlbumData] instance
     */
    override fun mapFromEntity(type: User): UserData {
        return UserData(
            id = type.id,
            name = type.name,
            username = type.username
        )
    }


}
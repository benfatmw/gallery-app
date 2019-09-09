package com.lin.mobile.gallery.presentation.mapper

import com.lin.mobile.gallery.domain.model.User
import com.lin.mobile.gallery.presentation.model.UserView
import javax.inject.Inject

/**
 * Map a [UserView] from a [User] instance when data is moving between
 * this layer and the Domain layer
 */
open class UserMapper @Inject constructor() : Mapper<User, UserView>() {
    /**
     * Map a [Photo] instance to a [PhotoView] instance
     */
    override fun mapToView(type: User): UserView {
        return UserView(
            id = type.id,
            name = type.name,
            username = type.username
        )
    }
}
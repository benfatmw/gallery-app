package com.lin.mobile.gallery.data.mapper

import com.lin.mobile.gallery.core.BaseMapper

/**
 * Abstract class for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <D> the cached model input type
 * @param <D> the remote model input type
 */
abstract class Mapper<M, E> : BaseMapper() {

    abstract fun mapFromEntity(type: M): E
    abstract fun mapToEntity(type: E): M

    fun mapFromEntity(values: List<M>): List<E> {
        return toList(values, ::mapFromEntity)
    }

    fun mapToEntity(values: List<E>): List<M> {
        return toList(values, ::mapToEntity)
    }
}
package com.lin.mobile.gallery.cache.mapper

import com.lin.mobile.gallery.core.BaseMapper

/**
 * Abstract class for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <C> the cached model input type
 * @param <D> the data model input type
 */
abstract class Mapper<C, D> : BaseMapper() {

    abstract fun mapFromData(type: D): C
    abstract fun mapToData(type: C): D

    fun mapFromData(values: List<D>): List<C> {
        return toList(values, ::mapFromData)
    }

    fun mapToData(values: List<C>): List<D> {
        return toList(values, ::mapToData)
    }
}
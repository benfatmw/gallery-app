package com.lin.mobile.gallery.presentation.mapper

import com.lin.mobile.gallery.core.BaseMapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer layers
 *
 * @param <O> the view model input type
 * @param <E> the domain model output type
 */
abstract class Mapper<E, O> : BaseMapper() {

    abstract fun mapToView(type: E): O

    fun mapToView(values: List<E>): List<O> {
        return toList(values, ::mapToView)
    }
}
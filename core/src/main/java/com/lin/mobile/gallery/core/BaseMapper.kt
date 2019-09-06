package com.lin.mobile.gallery.core

/**
 * Abstract class for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <D> the cached model input type
 * @param <D> the remote model input type
 */
abstract class BaseMapper {

    protected fun <T, U> toList(values: List<T>, op: (type: T) -> U): List<U> {
        val returnValues = mutableListOf<U>()
        for (value in values) {
            returnValues.add(op(value))
        }
        return returnValues
    }
}
package com.lin.mobile.gallery.ui.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer layers
 *
 * @param <V> the view input mimeType
 * @param <D> the view model output mimeType
 */
interface Mapper<out V, in D> {

    fun mapToViewModel(type: D): V

}
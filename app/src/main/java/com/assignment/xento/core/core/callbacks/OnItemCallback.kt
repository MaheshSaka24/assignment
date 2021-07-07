package com.assignment.xento.core.core.callbacks

interface OnItemCallback<T> {
    fun onItemClick(t: T, position: Int)
}
package com.assignment.xento.core.core.callbacks

interface OnFilesItemClick<T> {
    fun onItemClickListener(t: T, position: Int)
}
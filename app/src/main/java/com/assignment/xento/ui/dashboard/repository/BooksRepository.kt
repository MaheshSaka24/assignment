package com.assignment.xento.ui.dashboard.repository

import com.assignment.xento.api.book.ApiHelperBookImpl
import javax.inject.Inject

class BooksRepository @Inject constructor(private val apiHelperBookImpl: ApiHelperBookImpl) {
    suspend fun fetchBooks(hashMap: HashMap<String, String>) = apiHelperBookImpl.getBooks(hashMap)
}
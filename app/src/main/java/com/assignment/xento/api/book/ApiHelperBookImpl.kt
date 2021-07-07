package com.assignment.xento.api.book

import com.assignment.xento.model.books.Books
import javax.inject.Inject

class ApiHelperBookImpl @Inject constructor(
    private val apiService: ApiServiceBooks,
) : ApiHelperBook {
    override suspend fun getBooks(paramsMap: Map<String, String>): Books =
        apiService.getBooks(paramsMap)
}
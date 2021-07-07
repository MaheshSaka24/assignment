package com.assignment.xento.api.book

import com.assignment.xento.model.books.Books

interface ApiHelperBook {
    suspend fun getBooks(paramsMap: Map<String, String>): Books
}
package com.assignment.xento.api.book

import com.assignment.xento.model.books.Books
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiServiceBooks {
    // Get Books from API
    @GET("volumes")
    suspend fun getBooks(@QueryMap paramsMap: Map<String, String>): Books
}
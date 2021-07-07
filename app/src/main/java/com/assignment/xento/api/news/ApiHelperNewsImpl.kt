package com.assignment.xento.api.news

import com.assignment.xento.model.news.NewsResponce
import javax.inject.Inject

class ApiHelperNewsImpl @Inject constructor(
    private val apiService: ApiServiceNews,
) : ApiHelperNews {
    override suspend fun getNews(paramsMap: Map<String, String>): NewsResponce =
        apiService.getNews(paramsMap)
}
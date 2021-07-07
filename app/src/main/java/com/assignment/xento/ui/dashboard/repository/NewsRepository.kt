package com.assignment.xento.ui.dashboard.repository

import com.assignment.xento.api.news.ApiHelperNewsImpl
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiHelperNewsImpl: ApiHelperNewsImpl) {
    suspend fun fetchNews(hashMap: HashMap<String, String>) = apiHelperNewsImpl.getNews(hashMap)
}
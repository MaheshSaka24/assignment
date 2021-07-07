package com.assignment.xento.api.news

import com.assignment.xento.model.news.NewsResponce

interface ApiHelperNews {
    suspend fun getNews(paramsMap: Map<String, String>): NewsResponce
}
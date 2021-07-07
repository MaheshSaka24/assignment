package com.assignment.xento.api.news

import com.assignment.xento.model.news.NewsResponce
import retrofit2.http.*


interface ApiServiceNews {

    /**
     *  get news detail from news api
     *  https://newsapi.org/v2/everything?q=bitcoin&from=2021-06-03&sortBy=publishedAt&apiKey=_API_KEY
     */
    @GET("everything")
    suspend fun getNews(@QueryMap paramsMap: Map<String, String>): NewsResponce

}
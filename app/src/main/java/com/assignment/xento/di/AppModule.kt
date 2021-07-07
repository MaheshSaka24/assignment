package com.assignment.xento.di

import com.assignment.xento.BuildConfig
import com.assignment.xento.api.book.ApiHelperBook
import com.assignment.xento.api.news.ApiHelperNews
import com.assignment.xento.api.news.ApiServiceNews
import com.assignment.xento.api.book.ApiServiceBooks
import com.assignment.xento.other.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Named("news")
    fun provideBaseUrlNews() = Constants.BASE_URL_NEWS

    @Provides
    @Named("books")
    fun provideBaseUrlBooks() = Constants.BASE_URL_BOOKS

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    @Named("retrofitbook")
    fun provideRetrofitBook(
        okHttpClient: OkHttpClient,
        @Named("books") BASE_URL: String
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    @Named("retrofitnews")
    fun provideRetrofitNews(okHttpClient: OkHttpClient, @Named("news") BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideNewsApiService(@Named("retrofitnews") retrofit: Retrofit) = retrofit.create(
        ApiServiceNews::class.java
    )

    @Provides
    @Singleton
    fun provideBookApiService(@Named("retrofitbook") retrofit: Retrofit) = retrofit.create(
        ApiServiceBooks::class.java
    )

    @Provides
    @Singleton
    fun provideNewsApiHelper(apiNewsHelper: ApiHelperNews): ApiHelperNews = apiNewsHelper

    @Provides
    @Singleton
    fun provideBookApiHelper(apiBookHelper: ApiHelperBook): ApiHelperBook = apiBookHelper

}
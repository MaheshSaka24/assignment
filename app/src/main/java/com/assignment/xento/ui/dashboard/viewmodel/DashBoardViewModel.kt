package com.assignment.xento.ui.dashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.assignment.xento.core.core.BaseViewModel
import com.assignment.xento.other.Constants
import com.assignment.xento.other.Resource
import com.assignment.xento.ui.dashboard.repository.BooksRepository
import com.assignment.xento.ui.dashboard.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val booksRepository: BooksRepository
) : BaseViewModel() {

    val showProgrssBar = MutableLiveData<Boolean>()

    fun setProgressVisible(isProgressVisible: Boolean) {
        showProgrssBar.value = isProgressVisible
    }

    /**
     * fetch news by query with livedata
     */
    fun fetchNews(newsQuery: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val hashMap = HashMap<String, String>()
            hashMap[Constants.QUERY_NEWS] = newsQuery
            hashMap[Constants.QUERY_SORTBY] = "publishedAt"
            hashMap[Constants.QUERY_API_KEY] = Constants.API_KEY
            emit(Resource.success(data = newsRepository.fetchNews(hashMap)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    /**
     * books by query with livedata
     */
    fun fetchBooks(newsQuery: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val hashMap = HashMap<String, String>()
            hashMap[Constants.QUERY_VOLUMS] = newsQuery
            emit(Resource.success(data = booksRepository.fetchBooks(hashMap)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}
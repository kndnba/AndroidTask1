package com.bignerdranch.android.androidtask1.task3.presenter

import com.bignerdranch.android.androidtask1.task3.model.NewsRepository
import com.bignerdranch.android.androidtask1.task3.model.common.Common
import com.bignerdranch.android.androidtask1.task3.model.newsInterface.NewsResponse
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class NewsPresenter(
    val repository: NewsRepository
) : MvpPresenter<NewsContract>() {

    private val mService = Common.retrofitService

    val request = object : Callback<NewsResponse> {
        override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
            loadNews(response.body())
        }

        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
        }
    }

    private fun loadNews(response: NewsResponse?) {
        viewState.showLoading(false)
        viewState.loadNews(response?.news)
    }

    fun getNews(news: String) {
        viewState.showLoading(true)
        viewState.setTitle(news)
        when (news) {
            "software" -> getSoftwareNews()
            "entertainment" -> getEntertainmentNews()
            "health" -> getHealthNews()
            "science" -> getScienceNews()
            "sports" -> getSportsNews()
        }
    }

     private fun getSoftwareNews() {
         repository.getSoftwareNews(request)
    }

     private fun getEntertainmentNews() {
        repository.getEntertainmentNews(request)
    }

    private fun getHealthNews() {
        repository.getHealthNews(request)
    }

    private fun getScienceNews() {
        repository.getScienceNews(request)
    }

    private fun getSportsNews() {
        repository.getSportsNews(request)
    }

}
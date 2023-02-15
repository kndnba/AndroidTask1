package com.bignerdranch.android.androidtask1.task3.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bignerdranch.android.androidtask1.task3.model.common.Common
import com.bignerdranch.android.androidtask1.task3.model.newsInterface.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class NewsPresenter(
    private val view: NewsContract
) : MvpPresenter<NewsContract>() {

    private val mService = Common.retrofitService

    private val request = object : Callback<NewsResponse> {
        override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
            loadNews(response)
        }

        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
        }
    }

    private fun loadNews(response: Response<NewsResponse>) {
        view.showLoading(false)
        view.loadNews(response.body()?.news)
    }

    fun getNews(news: String) {
        when (news) {
            "software" -> getSoftwareNews()
            "entertainment" -> getEntertainmentNews()
            "health" -> getHealthNews()
            "science" -> getScienceNews()
            "sports" -> getSportsNews()
        }
    }

    private fun getSoftwareNews() {
        view.showLoading(true)
        mService.getSoftwareNewsList().enqueue(request)
        view.setTitle("Software")
    }

    private fun getEntertainmentNews() {
        view.showLoading(true)
        mService.getEntertainmentNewsList().enqueue(request)
        view.setTitle("Entertainment")
    }

    private fun getHealthNews() {
        view.showLoading(true)
        mService.getHealthNewsList().enqueue(request)
        view.setTitle("Health")
    }

    private fun getScienceNews() {
        view.showLoading(true)
        mService.getScienceNewsList().enqueue(request)
        view.setTitle("Science")
    }

    private fun getSportsNews() {
        view.showLoading(true)
        mService.getSportsNewsList().enqueue(request)
        view.setTitle("Sports")
    }

}
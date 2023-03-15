package com.bignerdranch.android.androidtask1.task3.model

import com.bignerdranch.android.androidtask1.task3.model.common.Common
import com.bignerdranch.android.androidtask1.task3.model.newsInterface.NewsResponse
import com.bignerdranch.android.androidtask1.task3.model.newsInterface.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    val service: RetrofitServices = Common.retrofitService


    fun getSoftwareNews(callback: Callback<NewsResponse>) =
        service.getSoftwareNewsList().enqueue(callback)

    fun getEntertainmentNews(callback: Callback<NewsResponse>) =
        service.getEntertainmentNewsList().enqueue(callback)

    fun getHealthNews(callback: Callback<NewsResponse>) =
        service.getHealthNewsList().enqueue(callback)

    fun getScienceNews(callback: Callback<NewsResponse>) =
        service.getHealthNewsList().enqueue(callback)

    fun getSportsNews(callback: Callback<NewsResponse>) =
        service.getHealthNewsList().enqueue(callback)
}
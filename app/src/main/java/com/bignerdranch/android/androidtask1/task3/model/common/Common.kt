package com.bignerdranch.android.androidtask1.task3.model.common

import com.bignerdranch.android.androidtask1.task3.model.newsInterface.RetrofitServices
import com.bignerdranch.android.androidtask1.task3.model.retrofit.RetrofitClient

object Common {
    const val BASE_URL = "https://newsapi.org/"
    val retrofitService : RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}
package com.bignerdranch.android.androidtask1.task3.common

import com.bignerdranch.android.androidtask1.task3.newsInterface.RetrofitServices
import com.bignerdranch.android.androidtask1.task3.retrofit.RetrofitClient

object Common {
    const val BASE_URL = "https://newsapi.org/"
    val retrofitService : RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}
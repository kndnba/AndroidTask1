package com.bignerdranch.android.androidtask1.task3.newsInterface

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("v2/everything?q=software&from=2023-01-24&sortBy=publishedAt&apiKey=c8bac5fab7c04264adf123ec2dd0d978")
    fun getSoftwareNewsList(): Call<NewsResponse>

    @GET("v2/everything?q=entertainment&from=2023-01-24&sortBy=publishedAt&apiKey=c8bac5fab7c04264adf123ec2dd0d978")
    fun getEntertainmentNewsList(): Call<NewsResponse>

    @GET("v2/everything?q=health&from=2023-01-24&sortBy=publishedAt&apiKey=c8bac5fab7c04264adf123ec2dd0d978")
    fun getHealthNewsList(): Call<NewsResponse>

    @GET("v2/everything?q=science&from=2023-01-24&sortBy=publishedAt&apiKey=c8bac5fab7c04264adf123ec2dd0d978")
    fun getScienceNewsList(): Call<NewsResponse>

    @GET("v2/everything?q=sports&from=2023-01-24&sortBy=publishedAt&apiKey=c8bac5fab7c04264adf123ec2dd0d978")
    fun getSportsNewsList(): Call<NewsResponse>
}
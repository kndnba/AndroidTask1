package com.bignerdranch.android.androidtask1.task3.retrofit

import com.bignerdranch.android.androidtask1.task3.common.Common
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

object RetrofitClient {
    private var retrofit : Retrofit? = null

    fun getClient(baseUrl : String): Retrofit {
        if (retrofit == null){
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl(Common.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return requireNotNull(retrofit)
    }
}
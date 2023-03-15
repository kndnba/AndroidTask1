package com.bignerdranch.android.androidtask1.task3.model.retrofit

import com.bignerdranch.android.androidtask1.task3.model.common.Common
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
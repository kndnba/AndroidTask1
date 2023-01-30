package com.bignerdranch.android.androidtask1.task3.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("author")
    val author: String? = null,

    @SerializedName("title")
    val title : String? = null,

    @SerializedName("publishedAt")
    val publishedAt : String? = null,

    @SerializedName("description")
    val description : String? = null,

    @SerializedName("urlToImage")
    val urlToImage : String? = null,

    @SerializedName("source")
    val source : Source?
)

package com.bignerdranch.android.androidtask1.task3.newsInterface

import com.bignerdranch.android.androidtask1.task3.model.News
import com.bignerdranch.android.androidtask1.task3.model.Source
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles")
    val news: List<News>,

    @SerializedName("source")
    val source: List<Source>

)

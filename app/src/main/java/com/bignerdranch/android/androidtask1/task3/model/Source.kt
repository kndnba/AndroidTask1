package com.bignerdranch.android.androidtask1.task3.model

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id : String? = null,

    @SerializedName("name")
    val name : String? = null
)

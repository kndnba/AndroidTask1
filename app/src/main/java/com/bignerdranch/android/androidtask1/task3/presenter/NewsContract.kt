package com.bignerdranch.android.androidtask1.task3.presenter

import com.bignerdranch.android.androidtask1.task3.model.News
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface NewsContract : MvpView {
fun loadNews(data: List<News>?)
fun showLoading(show: Boolean)
fun setTitle(text: String)
}
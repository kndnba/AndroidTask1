package com.bignerdranch.android.androidtask1.task3.presenter

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.bignerdranch.android.androidtask1.task3.model.News

@StateStrategyType(AddToEndStrategy::class)
interface NewsContract : MvpView {
fun loadNews(data: List<News>?)
fun showLoading(show: Boolean)
fun setTitle(text: String)
}
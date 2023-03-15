package com.bignerdranch.android.androidtask1.task2.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface ArtistContract : MvpView {
    fun loadGenre(text: String)
    fun loadArtist(text: String)
}
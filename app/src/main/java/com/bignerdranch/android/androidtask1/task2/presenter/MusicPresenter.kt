package com.bignerdranch.android.androidtask1.task2.presenter

import com.bignerdranch.android.androidtask1.task2.model.MusicProvider
import com.bignerdranch.android.androidtask1.task2.view.MusicActivity

class MusicPresenter {
    private val view = MusicActivity()
    private val model = MusicProvider()
}
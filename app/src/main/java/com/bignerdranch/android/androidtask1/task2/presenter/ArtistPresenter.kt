package com.bignerdranch.android.androidtask1.task2.presenter

import android.content.Context
import com.bignerdranch.android.androidtask1.task2.model.MusicProvider
import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class ArtistPresenter: MvpPresenter<ArtistContract>() {
    var artistAdapter = MusicAdapter()
    private val itemsList = artistAdapter.itemsList

    fun loadArtist(artist: String, context: Context) {
        val cursor = context.contentResolver.query(
            MusicProvider.CONTENT_URI,
            arrayOf(
                MusicProvider._ID,
                MusicProvider.SONG,
                MusicProvider.ARTIST,
                MusicProvider.GENRE,
                MusicProvider.PATH
            ),
            null,
            null,
            null
        )
        itemsList.clear()

        cursor?.let {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(2) == artist)
                        artistAdapter.addItems(
                            "${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} ${cursor.getString(4)}"
                        )
                }
                while (cursor.moveToNext())
            }
        }
    }

    fun loadGenre(genre: String, context: Context) {
        val cursor = context.contentResolver.query(
            MusicProvider.CONTENT_URI,
            arrayOf(
                MusicProvider._ID,
                MusicProvider.SONG,
                MusicProvider.ARTIST,
                MusicProvider.GENRE,
                MusicProvider.PATH
            ),
            null,
            null,
            null
        )
        itemsList.clear()
        cursor?.let {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(3) == genre)
                       artistAdapter.addItems(
                            "${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} ${cursor.getString(4)}"
                        )
                } while (cursor.moveToNext())
            }
        }
    }
}
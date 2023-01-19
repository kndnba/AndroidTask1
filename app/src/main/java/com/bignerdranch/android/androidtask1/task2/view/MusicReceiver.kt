package com.bignerdranch.android.androidtask1.task2.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.bignerdranch.android.androidtask1.task2.model.db.MyDBNames

class MusicReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val songName = intent?.getStringExtra(MyDBNames.SONG)
        val songArtist = intent?.getStringExtra(MyDBNames.ARTIST)
        val songGenre = intent?.getStringExtra(MyDBNames.GENRE)
        val startService = Intent(context, MyService::class.java)
        startService.apply {
            putExtra("songName", songName)
            putExtra("songArtist", songArtist)
            putExtra("songGenre", songGenre)
        }
        context?.startService(startService)
        context?.stopService(startService)
        context?.startService(startService)
    }
}
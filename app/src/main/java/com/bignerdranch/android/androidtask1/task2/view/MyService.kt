package com.bignerdranch.android.androidtask1.task2.view

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

class MyService : Service(), MediaPlayer.OnPreparedListener {
    private var musicPlayer: MediaPlayer? = null

    inner class LocalBinder : Binder() {
        fun getService(): MyService = this@MyService
    }
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val songName = intent.getStringExtra("songName")
        initPlayer(songName)
        if (musicPlayer?.isPlaying == true) {
            musicPlayer?.pause()
        } else {
            musicPlayer?.start()
        }
        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy(){
        super.onDestroy()
        musicPlayer?.release()
        val intent = Intent(this, MusicActivity::class.java)
        val songName = intent.getStringExtra("songName")
        if (songName != null) {
            initPlayer(songName)
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.prepareAsync()
    }

    private fun initPlayer(songName: String?) {
        if (songName == null){
            Toast.makeText(this, "Pick a song", Toast.LENGTH_SHORT).show()
        } else {
            val resID = resources.getIdentifier(songName, "raw", packageName)
            if (musicPlayer == null) {
                musicPlayer = MediaPlayer.create(this, resID)
                musicPlayer?.isLooping = true
                musicPlayer?.start()
            } else {
                musicPlayer?.start()
            }
        }
    }
    fun pause() {
            musicPlayer?.pause()
    }

}
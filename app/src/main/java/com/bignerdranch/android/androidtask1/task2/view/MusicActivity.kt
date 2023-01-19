package com.bignerdranch.android.androidtask1.task2.view

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import com.bignerdranch.android.androidtask1.databinding.ActivityMusicBinding

class MusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicBinding
    private lateinit var playButton: Button
    private lateinit var stopButton: Button
    private lateinit var pauseButton: Button
    private lateinit var chooseArtistButton: Button
    private val receiver = MusicReceiver()
    private var myService : MyService? = null
    private var bound = false
    private var sConn: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playButton = binding.play
        stopButton = binding.stop
        pauseButton = binding.pause
        chooseArtistButton = binding.chooseArtist

        val intentArtistActivity = Intent(this, ArtistActivity::class.java)

        chooseArtistButton.setOnClickListener {
            startActivity(intentArtistActivity)
        }
        initMusicService()

        val intentFilter = IntentFilter(ArtistActivity.SONG_DATA).also {
            registerReceiver(receiver, it)
        }
            val intent = Intent(this, MusicReceiver::class.java)
            val textViewSongName: TextView = binding.songTitle
            val textViewGenre: TextView = binding.genreTitle
            val textViewArtist: TextView = binding.artistTitle

            val songName = intent.getStringExtra("songName")
            val songGenre = intent.getStringExtra("songGenre")
            val songArtist = intent.getStringExtra("songArtist")

            textViewSongName.text = songName
            textViewGenre.text = songGenre
            textViewArtist.text = songArtist
        sConn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, binder: IBinder) {
                bound = true
                myService = (binder as MyService.LocalBinder).getService()
            }

            override fun onServiceDisconnected(name: ComponentName) {
                bound = false
            }
        }
    }

    private fun initMusicService() {
        val intentService = Intent(this, MyService::class.java)

        playButton.setOnClickListener {
            startService(intentService)
        }

        pauseButton.setOnClickListener {
            myService?.pause()
        }

        stopButton.setOnClickListener {
            stopService(intentService)
        }
    }
}
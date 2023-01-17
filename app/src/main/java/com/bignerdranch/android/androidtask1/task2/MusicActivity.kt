package com.bignerdranch.android.androidtask1.task2

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.bignerdranch.android.androidtask1.MyService
import com.bignerdranch.android.androidtask1.databinding.ActivityMusicBinding
import com.bignerdranch.android.androidtask1.task2.db.MyDBNames

class MusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicBinding
    private lateinit var playButton: Button
    private lateinit var stopButton: Button
    private lateinit var pauseButton: Button
    private lateinit var chooseArtistButton: Button
    private val receiver = MusicReceiver()

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
        val songArtist = intent.getStringExtra("songArtist")
        val songGenre = intent.getStringExtra("songGenre")
        val songPath = intent.getStringExtra("songPath")

        textViewSongName.text = ("Song: $songName")
        textViewGenre.text = ("Genre: $songGenre")
        textViewArtist.text = ("Artist: $songArtist")
    }

    private fun initMusicService() {
        val intentService = Intent(this, MyService::class.java)

        playButton.setOnClickListener {
            startService(intentService)
        }

        pauseButton.setOnClickListener {
            startService(intentService)
        }

        stopButton.setOnClickListener {
            stopService(intentService)
        }
    }
}
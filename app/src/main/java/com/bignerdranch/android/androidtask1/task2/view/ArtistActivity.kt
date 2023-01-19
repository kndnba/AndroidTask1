package com.bignerdranch.android.androidtask1.task2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.androidtask1.R
import com.bignerdranch.android.androidtask1.databinding.ActivityArtistBinding
import com.bignerdranch.android.androidtask1.task2.model.MusicProvider
import com.bignerdranch.android.androidtask1.task2.presenter.MusicAdapter

class ArtistActivity : AppCompatActivity() {
    private val itemsList = ArrayList<String>()
    private lateinit var artistAdapter: MusicAdapter
    private lateinit var binding: ActivityArtistBinding

    companion object {
        const val SONG_DATA = "com.bignerdranch.android.androidtask1.task2.SONG_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistBinding.inflate(layoutInflater)

        contentResolver.query(
            MusicProvider.CONTENT_URI,
            arrayOf(MusicProvider._ID, MusicProvider.SONG, MusicProvider.ARTIST),
            null,
            null,
            null
        )

        setContentView(binding.root)
        val artists = resources.getStringArray(R.array.Artists)
        val genres = resources.getStringArray(R.array.Genres)
        val artistSpinner = binding.artistSpinner
        val recyclerViewSongs = binding.recyclerViewSongs
        artistAdapter = MusicAdapter(itemsList) {
            finish()
        }
        recyclerViewSongs.layoutManager = LinearLayoutManager(this)
        recyclerViewSongs.adapter = artistAdapter
        val artistAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, artists)
        artistSpinner.adapter = artistAdapter

        artistSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                itemsList.clear()
                loadArtist(artists[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                showToast("Nothing selected")
            }
        }

        val genreSpinner = binding.genreSpinner
        val genreAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genres)
        genreSpinner.adapter = genreAdapter
        genreSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                itemsList.clear()
                loadGenre(genres[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                showToast("Nothing selected")
            }
        }
    }

    private fun loadArtist(artist: String) {
        val cursor = contentResolver.query(
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
        cursor?.let {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(2) == artist)
                        itemsList.add(
                            "${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} ${cursor.getString(4)}"
                        )
                } while (cursor.moveToNext())}
        }
        artistAdapter.notifyDataSetChanged()
    }

    private fun loadGenre(genre: String) {
        val cursor = contentResolver.query(
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
        cursor?.let {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(3) == genre)
                        itemsList.add(
                            "${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} ${cursor.getString(4)}"
                        )
                } while (cursor.moveToNext())
            }
        }

        artistAdapter.notifyDataSetChanged()
    }
   private fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
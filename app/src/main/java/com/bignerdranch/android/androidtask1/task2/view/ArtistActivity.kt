package com.bignerdranch.android.androidtask1.task2.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.androidtask1.R
import com.bignerdranch.android.androidtask1.databinding.ActivityArtistBinding
import com.bignerdranch.android.androidtask1.task2.model.MusicProvider
import com.bignerdranch.android.androidtask1.task2.presenter.ArtistContract
import com.bignerdranch.android.androidtask1.task2.presenter.ArtistPresenter
import com.bignerdranch.android.androidtask1.task2.presenter.MusicAdapter
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ArtistActivity : MvpAppCompatActivity(), ArtistContract {
    private lateinit var binding: ActivityArtistBinding

    @InjectPresenter
    lateinit var artistPresenter: ArtistPresenter

    @ProvidePresenter
    fun providePresenter() = ArtistPresenter()

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
        val recyclerViewSongs = binding.recyclerViewSongs


        recyclerViewSongs.layoutManager = LinearLayoutManager(this)
        recyclerViewSongs.adapter = artistPresenter.artistAdapter
        val artistAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.Artists)
        )
        binding.artistSpinner.adapter = artistAdapter

        val genreAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.Genres)
        )
        binding.genreSpinner.adapter = genreAdapter
        selectArtist()
        selectGenre()
        artistPresenter.artistAdapter.setListener {
            finish()
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    override fun loadGenre(text: String) {
        artistPresenter.loadGenre(text, this)
    }

    override fun loadArtist(text: String) {
        artistPresenter.loadArtist(text, this)
    }

     fun selectArtist() {
        val artists: Array<String> = resources.getStringArray(R.array.Artists)
        binding.artistSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                loadArtist(artists[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun selectGenre() {
        val genres: Array<String> = resources.getStringArray(R.array.Genres)
        binding.genreSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                loadGenre(genres[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }
}
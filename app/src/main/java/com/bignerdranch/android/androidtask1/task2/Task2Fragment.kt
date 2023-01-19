package com.bignerdranch.android.androidtask1.task2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.androidtask1.databinding.FragmentTask2Binding
import android.widget.Button
import com.bignerdranch.android.androidtask1.task2.view.MusicActivity


class Task2Fragment : Fragment() {
    private lateinit var binding: FragmentTask2Binding
    private lateinit var startMusicActivity: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask2Binding.inflate(layoutInflater)
        startMusicActivity = binding.startMusicActivity
        val intent = Intent(context, MusicActivity::class.java)

        startMusicActivity.setOnClickListener {
            startActivity(intent)
        }

        return binding.root
    }
}

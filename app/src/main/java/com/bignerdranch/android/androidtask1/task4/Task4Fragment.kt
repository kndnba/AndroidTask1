package com.bignerdranch.android.androidtask1.task4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.androidtask1.R
import com.bignerdranch.android.androidtask1.databinding.FragmentTask3Binding
import com.bignerdranch.android.androidtask1.databinding.FragmentTask4Binding

class Task4Fragment : Fragment() {
    private lateinit var binding: FragmentTask4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask4Binding.inflate(layoutInflater)
        binding.apply {
            circleCenter.isClickable
        }
        return binding.root
    }
}
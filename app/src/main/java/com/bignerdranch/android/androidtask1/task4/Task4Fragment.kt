package com.bignerdranch.android.androidtask1.task4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.androidtask1.R
import com.bignerdranch.android.androidtask1.databinding.FragmentTask4Binding

class Task4Fragment : Fragment() {
    private lateinit var binding: FragmentTask4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask4Binding.inflate(layoutInflater)
        val fragment = MapsFragment()

        binding.apply {
            circleCenter.setOnClickListener {
                (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.animation, R.anim.animation_close)
                    .replace(R.id.main_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
        return binding.root
    }
}
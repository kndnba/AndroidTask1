package com.bignerdranch.android.androidtask1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bignerdranch.android.androidtask1.databinding.FragmentBlankBinding
import com.bignerdranch.android.androidtask1.databinding.FragmentTask1Binding

class Task1Fragment : Fragment() {
    private lateinit var binding: FragmentTask1Binding
    private lateinit var textWatcherFragmentButton: Button
    private lateinit var recyclerViewFragmentButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask1Binding.inflate(layoutInflater)
        textWatcherFragmentButton = binding.textWatcherFragment
        recyclerViewFragmentButton = binding.recyclerViewFragment
        var fragment: Fragment
        textWatcherFragmentButton.setOnClickListener{
                fragment = BlankFragment()
                childFragmentManager.beginTransaction()
                    .replace(R.id.task1_container, fragment)
                    .commit()
        }

        recyclerViewFragmentButton.setOnClickListener{
                fragment = RecyclerViewFragment()
                childFragmentManager.beginTransaction()
                    .replace(R.id.task1_container, fragment)
                    .commit()
        }
        return binding.root
    }
}
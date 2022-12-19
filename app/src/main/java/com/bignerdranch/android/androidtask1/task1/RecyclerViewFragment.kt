package com.bignerdranch.android.androidtask1.task1

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidtask1.R
import com.bignerdranch.android.androidtask1.adapter.MyAdapter
import com.bignerdranch.android.androidtask1.databinding.FragmentRecyclerViewBinding

private const val TAG = "RecyclerViewFragment"

class RecyclerViewFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView() called")
        binding = FragmentRecyclerViewBinding.inflate(layoutInflater)
        val recyclerView: RecyclerView = binding.recyclerViewFragment
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MyAdapter(getFiguresList(), requireContext())
        return binding.root
    }

    private fun getFiguresList(): List<String> {
        return this.resources.getStringArray(R.array.figure_names).toList()
    }
}
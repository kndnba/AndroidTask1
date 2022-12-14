package com.bignerdranch.android.androidtask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidtask1.adapter.MyAdapter
import com.bignerdranch.android.androidtask1.databinding.ActivityRecyclerViewBinding


class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(getFiguresList(), this)
    }

    private fun getFiguresList(): List<String> {
        return this.resources.getStringArray(R.array.figure_names).toList()
    }
}
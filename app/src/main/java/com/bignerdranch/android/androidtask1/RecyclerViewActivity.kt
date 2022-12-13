package com.bignerdranch.android.androidtask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidtask1.adapter.MyAdapter
import com.bignerdranch.android.androidtask1.databinding.ActivityRecyclerViewBinding


class RecyclerViewActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val recyclerView: RecyclerView = mBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(getFiguresList(), this)
    }

    private fun getFiguresList(): List<String> {
        return this.resources.getStringArray(R.array.figure_names).toList()
    }
}
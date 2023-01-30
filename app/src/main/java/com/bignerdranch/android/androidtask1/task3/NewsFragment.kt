package com.bignerdranch.android.androidtask1.task3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.androidtask1.databinding.FragmentNewsBinding
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        val title = binding.newsTitle
        val source = binding.sourceName
        val description = binding.newsDescription
        val bundle = this.arguments
        val imageGlide = binding.imageFullNewsGlide
        val imagePicasso = binding.imageFullNewsPicasso

        if (bundle != null) {
            title.text = bundle.getString("title")
            source.text = bundle.getString("name")
            description.text = bundle.getString("description")
            val imageValue = bundle.getString("image")

            Glide.with(this)
                .load(imageValue)
                .into(imageGlide)

            Picasso.get()
                .load(imageValue)
                .into(imagePicasso)
        }
        return binding.root
    }
}
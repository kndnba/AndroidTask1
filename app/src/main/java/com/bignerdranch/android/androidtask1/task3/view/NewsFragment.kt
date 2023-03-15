package com.bignerdranch.android.androidtask1.task3.view

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
        binding.apply {
            val bundle = arguments
            if (bundle != null) {
                newsTitle.text = bundle.getString("title")
                sourceName.text = bundle.getString("name")
                newsDescription.text = bundle.getString("description")
                val imageValue = bundle.getString("image")

                Glide.with(requireContext())
                    .load(imageValue)
                    .into(imageFullNewsGlide)

                Picasso.get()
                    .load(imageValue)
                    .into(imageFullNewsPicasso)
            }
            return root
        }
    }
}

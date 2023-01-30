package com.bignerdranch.android.androidtask1.task3.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidtask1.R
import com.bignerdranch.android.androidtask1.task3.NewsFragment
import com.bignerdranch.android.androidtask1.task3.model.News
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_news.view.*

class NewsAdapter (private val newsList : List<News>) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.image_news
        val title : TextView = itemView.txt_title
        val publishedAt : TextView = itemView.txt_publishedAt
        val author: TextView = itemView.txt_author
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_news, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        fun bind(listItem : News){
            val fragment = NewsFragment()
            val context = holder.itemView.context
            val bundle = Bundle()

            holder.itemView.setOnClickListener{
                bundle.putString("image", listItem.urlToImage)
                bundle.putString("author", listItem.author)
                bundle.putString("title", listItem.title)
                bundle.putString("publishedAt", listItem.publishedAt)
                bundle.putString("description", listItem.description)
                bundle.putString("name", listItem.source?.name)

                fragment.arguments = bundle
                (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
        val listItem = newsList[position]
        bind(listItem)
        Glide.with(holder.image).load(newsList[position].urlToImage).into(holder.image)
        holder.author.text = newsList[position].author
        holder.title.text = newsList[position].title
        holder.publishedAt.text = newsList[position].publishedAt
    }

    override fun getItemCount(): Int = newsList.size
}
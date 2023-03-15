package com.bignerdranch.android.androidtask1.task2.presenter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidtask1.R
import com.bignerdranch.android.androidtask1.task2.view.ArtistActivity.Companion.SONG_DATA
import com.bignerdranch.android.androidtask1.task2.model.db.MyDBNames
import com.bignerdranch.android.androidtask1.task3.model.News

class MusicAdapter: RecyclerView.Adapter<MusicAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView? = view.findViewById(R.id.musicTextView)
    }
    var itemsList : MutableList<String> = arrayListOf()
    var listener: () -> Unit = {}

    fun addItems(data: String?) {
        data?.let {
            itemsList.addAll((listOf(it)))
            notifyDataSetChanged()
        }
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater.inflate(R.layout.item_layout_music, parent, false))
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    @JvmName("setListener1")
    fun setListener(listener: () -> Unit) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.textView?.text = item
        holder.textView?.setOnClickListener {

            val context = holder.textView.context
            val intent = Intent()
            val array = itemsList[position].split(" ")

            intent.apply {
                putExtra(MyDBNames.TABLE_NAME, itemsList[position])
                putExtra(MyDBNames.SONG, array[0])
                putExtra(MyDBNames.ARTIST, array[1])
                putExtra(MyDBNames.GENRE, array[2])
                putExtra(MyDBNames.PATH, array[3])
            }

            intent.action = SONG_DATA
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            context.sendBroadcast(intent)
            listener.invoke()
        }
    }
}
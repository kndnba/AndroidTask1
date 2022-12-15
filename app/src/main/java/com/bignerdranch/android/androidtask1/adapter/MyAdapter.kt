package com.bignerdranch.android.androidtask1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidtask1.task1.DialogFragment
import com.bignerdranch.android.androidtask1.R


class MyAdapter(private val names: List<String>, private val context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.figureName)
        val layout: ConstraintLayout = itemView.findViewById(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = names[position]
        holder.layout.setOnClickListener{
            val myDialogFragment = DialogFragment()
            val manager = (context as AppCompatActivity).supportFragmentManager
            myDialogFragment.show(manager, "myDialog")

            myDialogFragment.index = position

            myDialogFragment.message = when (names[position]){
                "Квадрат" -> "Первый"
                "Треугольник" -> "Второй"
                "Параллелепипед" -> "Третий"
                "Тетраэдр" -> "Четвертый"
                "Круг" -> "Пятый"
                "Трапеция" -> "Шестой"
                "Куб" -> "Седьмой"
                "Овал" -> "Восьмой"
                "Ромб" -> "Девятый"
                "Окружность" -> "Десятый"
                else -> {""}
            }
        }
    }
    override fun getItemCount() = names.size
}
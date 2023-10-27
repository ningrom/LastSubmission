package com.dicoding.magazine

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class listSmartphoneAdapter(private val context: Context, private val smartphone: List<Smartphone>, val listener: (Smartphone) -> Unit)
    : RecyclerView.Adapter<listSmartphoneAdapter.ListViewHolder>() {

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val photo = view.findViewById<ImageView>(R.id.img_item_photo)
        val name = view.findViewById<TextView>(R.id.tv_item_name)
        val description = view.findViewById<TextView>(R.id.tv_item_description)

        @SuppressLint("SetTextI18n")
        fun bindView(smartphone: Smartphone, listener: (Smartphone) -> Unit) {
            name.text = smartphone.name
            description.text = smartphone.description
            photo.setImageResource(smartphone.photo)
            itemView.setOnClickListener {
                listener(smartphone)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_row_smartphone, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindView(smartphone[position], listener)
    }

    override fun getItemCount(): Int = smartphone.size
}


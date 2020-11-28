package com.example.daggertutorial.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggertutorial.R
import com.example.daggertutorial.model.User

class MainAdapter(private val usersList: ArrayList<User>)
    : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(user: User){
            itemView.findViewById<TextView>(R.id.textViewUserName).text = user.name
            itemView.findViewById<TextView>(R.id.textViewUserEmail).text = user.email
            Glide.with(itemView.context)
                .load(user.avatar)
                .into(itemView.findViewById(R.id.imageViewAvatar))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int = usersList.size

    fun addData(list: List<User>) {
        usersList.addAll(list)
    }
}
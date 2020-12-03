package com.example.assignment3.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


import com.example.assignment3.R
import com.example.assignment3.data.db.AnimalDbModel

class AnimalItemAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listOfAnimals = listOf<AnimalDbModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AnimalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.animal_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = listOfAnimals.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as AnimalViewHolder
        movieViewHolder.bindView(listOfAnimals[position])
    }

    fun setAnimalList(listOfBooks: List<AnimalDbModel>) {
        this.listOfAnimals = listOfBooks
        notifyDataSetChanged()
    }
}
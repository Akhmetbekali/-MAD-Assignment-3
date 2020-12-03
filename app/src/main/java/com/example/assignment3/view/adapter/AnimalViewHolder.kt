package com.example.assignment3.view.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.assignment3.R

import com.example.assignment3.data.db.AnimalDbModel
import com.example.assignment3.view.SearchFragmentDirections
import kotlinx.android.synthetic.main.animal_list_item.view.*

class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(animalItem: AnimalDbModel) {

        Glide.with(itemView.context)
            .load(animalItem.photoSmallUrl)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.no_image)
            .into(itemView.imageView)
        itemView.breedTextView.text = animalItem.breed
        itemView.animalNameTextView.text = animalItem.name
        itemView.animalDescriptionTextView.text = animalItem.description
        itemView.animalAgeTextView.text = animalItem.age
        itemView.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(animalItem)
            it.findNavController().navigate(action)
        }
    }
}
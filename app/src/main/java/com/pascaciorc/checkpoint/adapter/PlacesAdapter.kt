package com.pascaciorc.checkpoint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pascaciorc.checkpoint.data.Place
import com.pascaciorc.checkpoint.databinding.PlaceItemBinding

class PlacesAdapter(private val places: List<Place>) :
    RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PlaceItemBinding.inflate(layoutInflater, parent, false)

        return PlacesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.binding.place = places[position]
    }

    override fun getItemCount() = places.size

    inner class PlacesViewHolder(val binding: PlaceItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
package com.pascaciorc.checkpoint.adapter

import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pascaciorc.checkpoint.PlacesFragmentDirections
import com.pascaciorc.checkpoint.data.Place
import com.pascaciorc.checkpoint.data.PlaceItem
import com.pascaciorc.checkpoint.data.toPlaceItem
import com.pascaciorc.checkpoint.databinding.PlaceItemBinding

class PlacesAdapter(private val places: List<Place>, private val location: Location) :
    RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PlaceItemBinding.inflate(layoutInflater, parent, false)

        return PlacesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        val place = places[position]
        holder.binding.place = place

        holder.itemView.setOnClickListener {
            it.navigateToDetails(place.toPlaceItem())
        }
    }

    override fun getItemCount() = places.size

    private fun View.navigateToDetails(item: PlaceItem) {
        val action = PlacesFragmentDirections
            .actionPlacesFragmentToInformationFragment(
                item = item,
                location = location
            )
        findNavController().navigate(action)
    }

    inner class PlacesViewHolder(val binding: PlaceItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
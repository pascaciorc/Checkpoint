package com.pascaciorc.checkpoint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pascaciorc.checkpoint.data.Checkpoint
import com.pascaciorc.checkpoint.databinding.CheckpointItemBinding

class CheckpointAdapter(private val checkpoints: List<Checkpoint>) :
    RecyclerView.Adapter<CheckpointAdapter.CheckpointViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckpointViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CheckpointItemBinding.inflate(layoutInflater, parent, false)

        return CheckpointViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckpointViewHolder, position: Int) {
        holder.binding.checkpoint = checkpoints[position]
    }

    override fun getItemCount() = checkpoints.size

    inner class CheckpointViewHolder(val binding: CheckpointItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
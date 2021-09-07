package com.pascaciorc.checkpoint.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.pascaciorc.checkpoint.R

@BindingAdapter("createdDate", "visitedDate")
fun bindDate(view: TextView, createdDate: String, visitedDate: String) {
    if (visitedDate.isNotEmpty()) {
        view.text = view.resources.getString(R.string.visited_on, visitedDate)
    } else {
        view.text = view.resources.getString(R.string.created_on, createdDate)

    }
}

@BindingAdapter("visitedDate")
fun bindPin(view: ImageView, visitedDate: String) {
    if (visitedDate.isNotEmpty()) {
        view.setImageResource(R.drawable.ic_visited_checkpoint)
    } else {
        view.setImageResource(R.drawable.ic_checkpoint)
    }
}
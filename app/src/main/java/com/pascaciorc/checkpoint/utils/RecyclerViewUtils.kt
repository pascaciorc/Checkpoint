package com.pascaciorc.checkpoint.utils

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setUp(context: Context) {
    val layoutManager = LinearLayoutManager(
        context,
        RecyclerView.VERTICAL,
        false
    )

    val divider = DividerItemDecoration(
        context,
        layoutManager.orientation
    )

    this.layoutManager = layoutManager
    this.addItemDecoration(divider)
}
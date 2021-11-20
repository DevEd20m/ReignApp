package com.faztbit.reignapp.ui.main.adapter

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.faztbit.domain.models.HitsDomain
import com.faztbit.reignapp.R

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
    private var textViewNameClient: TextView = itemView.findViewById(R.id.textViewNameClient)
    private var textViewCreateAt: TextView = itemView.findViewById(R.id.textViewCreateAt)

    fun bind(data: HitsDomain) =
        with(itemView) {
            textViewTitle.text = data.title
            textViewNameClient.text = data.author
            textViewCreateAt.isVisible = true
            textViewCreateAt.text = data.createAt


        }
}
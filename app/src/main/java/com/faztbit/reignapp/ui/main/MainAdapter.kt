package com.faztbit.reignapp.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faztbit.domain.models.HitsDomain
import com.faztbit.reignapp.R
import com.faztbit.reignapp.databinding.ItemHitsBinding
import com.faztbit.reignapp.extensions.basicDiffUtil
import com.faztbit.reignapp.extensions.bindingInflate

class MainAdapter(private val listener: (HitsDomain) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var list: List<HitsDomain> by basicDiffUtil(
        emptyList(), { old, new -> old.objectId == new.objectId }
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(parent.bindingInflate(R.layout.item_hits, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.dataBinding.item = item
        holder.itemView.setOnClickListener {
            listener.invoke(item)
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(val dataBinding: ItemHitsBinding) :
        RecyclerView.ViewHolder(dataBinding.root)
}
package com.faztbit.reignapp.ui.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.faztbit.domain.models.HitsDomain
import com.faztbit.reignapp.ui.main.adapter.ViewAdapter

@BindingAdapter("listData")
fun RecyclerView.setItems(data: List<HitsDomain>?) {
    (adapter as? ViewAdapter)?.let {
        it.items = data?.toMutableList() ?: mutableListOf()
        it.notifyDataSetChanged()
    }
}
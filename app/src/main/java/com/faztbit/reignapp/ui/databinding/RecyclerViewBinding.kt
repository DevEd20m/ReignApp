package com.faztbit.reignapp.ui.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.faztbit.domain.models.HitsDomain
import com.faztbit.reignapp.ui.main.MainAdapter

@BindingAdapter("listData")
fun RecyclerView.setItems(data: List<HitsDomain>?) {
    (adapter as? MainAdapter)?.let {
        it.list = data ?: emptyList()
    }
}
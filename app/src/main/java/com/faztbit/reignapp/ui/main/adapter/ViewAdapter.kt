package com.faztbit.reignapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faztbit.domain.models.HitsDomain
import com.faztbit.reignapp.R
import java.util.*

class ViewAdapter(private val listener: (HitsDomain) -> Unit) :
    RecyclerView.Adapter<ItemViewHolder>(),
    ItemTouchHelperCallback.ItemTouchHelperAdapter {

    var items: MutableList<HitsDomain> = arrayListOf()

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(parent.inflate(R.layout.item_hits))

    override fun onItemMoved(fromPosition: Int, toPosition: Int) =
        swapItems(fromPosition, toPosition)

    override fun onItemDismiss(position: Int) = deleteItem(position)

    private fun deleteItem(position: Int) {
        listener.invoke(items[position])
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun swapItems(positionFrom: Int, positionTo: Int) {
        Collections.swap(items, positionFrom, positionTo)
        notifyItemMoved(positionFrom, positionTo)
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

}
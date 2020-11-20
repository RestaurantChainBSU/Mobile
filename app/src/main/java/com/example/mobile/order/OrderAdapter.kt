package com.example.mobile.order

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.mobile.R
import com.example.mobile.network.dishes.DishesItem
import com.google.android.material.shape.CornerFamily
import kotlinx.android.synthetic.main.dishes_item.view.*

class OrderAdapter : ListAdapter<DishesItem, OrderAdapter.ItemViewholder>(DiffCallback())  {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.dishes_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DishesItem) = with(itemView) {
            imageView.load(item.image_link) {
                crossfade(true)
            }
            imageView.shapeAppearanceModel = imageView.shapeAppearanceModel
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, 32F)
                .build()
            textView4.text = item.dish_name
            textView.text = "${item.price} BYN"
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<DishesItem>() {
    override fun areItemsTheSame(oldItem: DishesItem, newItem: DishesItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DishesItem, newItem: DishesItem): Boolean {
        return oldItem == newItem
    }
}
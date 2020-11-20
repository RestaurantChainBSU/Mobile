package com.example.mobile


import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import coil.load
import com.google.android.material.shape.CornerFamily
import kotlinx.android.synthetic.main.dishes_item.view.*

class DishAdapter : ListAdapter<DishItem, DishAdapter.ItemViewholder>(DiffCallback())  {

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
        fun bind(item: DishItem) = with(itemView) {
            imageView.load(item.url) {
                crossfade(true)
            }
            imageView.shapeAppearanceModel = imageView.shapeAppearanceModel
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, 32F)
                .build()
            textView4.text = item.name
            textView.text = "${item.price} BYN"
            setOnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                val b = Bundle()
                b.putString("name", item.name)
                b.putString("url", item.url)
                intent.putExtras(b)
                context.startActivity(intent)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<DishItem>() {
    override fun areItemsTheSame(oldItem: DishItem, newItem: DishItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DishItem, newItem: DishItem): Boolean {
        return oldItem == newItem
    }
}
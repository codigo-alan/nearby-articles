package com.example.nearbyarticles.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.nearbyarticles.R
import com.example.nearbyarticles.databinding.ItemBinding
import com.example.nearbyarticles.domain.model.Item
import com.example.nearbyarticles.utils.limitLength

class ItemAdapter (private var items: List<Item>, private val listener: OnClickListener)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemBinding.bind(view)

        fun setListener(item: Item){
            binding.root.setOnClickListener {
                listener.onClick(item)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val item = items[position]
        with(holder){
            setListener(item)
            binding.tvName.text = item.title.limitLength(25) //my created extension function

            Glide.with(context)
                .load(item.thumbnail?.source)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //save in cache to avoid unneeded resources consume
                .centerCrop()
                .circleCrop()
                .error(R.drawable.baseline_broken_image_24)
                .into(binding.ivImage) //put the image in te image view

        }
    }

    fun setItems(newItemList: List<Item>) {
        items = newItemList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
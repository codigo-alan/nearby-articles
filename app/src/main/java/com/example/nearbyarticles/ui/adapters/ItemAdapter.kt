package com.example.nearbyarticles.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbyarticles.R
import com.example.nearbyarticles.databinding.ItemBinding
import com.example.nearbyarticles.domain.model.Item

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
            binding.tvName.text = "${item.title}"
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
package com.dessy.screeningtest.ui.event

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dessy.screeningtest.databinding.ItemEventHorizontalBinding
import com.dessy.screeningtest.model.EventEntity

class HorizontalEventAdapter (private val list: List<EventEntity>): RecyclerView.Adapter<HorizontalEventAdapter.EventAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapterViewHolder {
        val itemEventHorizontalBinding = ItemEventHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventAdapterViewHolder(itemEventHorizontalBinding, parent.context)
    }

    override fun onBindViewHolder(holder: EventAdapterViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    class EventAdapterViewHolder(private val binding: ItemEventHorizontalBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EventEntity) {
            Glide.with(context)
                .load(item.image)
                .into(binding.imgEventHorizontal)

            binding.tvNamaEvent.text = item.event

            itemView.setOnClickListener {
                Toast.makeText(context, "Lat: ${item.lat} \n Long: ${item.long}", Toast.LENGTH_SHORT).show()
            }

        }
    }
}

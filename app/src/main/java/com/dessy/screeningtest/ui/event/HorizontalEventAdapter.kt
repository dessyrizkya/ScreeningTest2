package com.dessy.screeningtest.ui.event

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dessy.screeningtest.databinding.ItemEventHorizontalBinding
import com.dessy.screeningtest.model.EventEntity
import org.osmdroid.api.IMapController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay

class HorizontalEventAdapter (private val list: List<EventEntity>, private val listener: EvenAdapterClickListener): RecyclerView.Adapter<HorizontalEventAdapter.EventAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapterViewHolder {
        val itemEventHorizontalBinding = ItemEventHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventAdapterViewHolder(itemEventHorizontalBinding, parent.context, listener)
    }

    override fun onBindViewHolder(holder: EventAdapterViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    class EventAdapterViewHolder(private val binding: ItemEventHorizontalBinding, private val context: Context, private val listener: EvenAdapterClickListener): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EventEntity) {
            Glide.with(context)
                .load(item.image)
                .into(binding.imgEventHorizontal)

            binding.tvNamaEvent.text = item.event

            itemView.setOnClickListener {
                listener.onItemClickListener(item)
            }


        }
    }
}

package com.dessy.screeningtest.ui.event

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dessy.screeningtest.databinding.ItemEventBinding
import com.dessy.screeningtest.model.EventEntity
import com.dessy.screeningtest.model.UserEntity
import com.dessy.screeningtest.ui.EventGuestActivity
import com.dessy.screeningtest.util.Extra.EXTRA_USER

class EventAdapter (private val list: List<EventEntity>, private val userEntity: UserEntity): RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemEventBinding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(itemEventBinding, parent.context, userEntity)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int  = list.size

    inner class EventViewHolder(private val binding: ItemEventBinding, private val context: Context,  private val userEntity: UserEntity): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventEntity) {
            with(binding){
                Glide.with(context)
                    .load(item.image)
                    .into(imgEvent)

                tvNamaEvent.text =item.event
                tvTglEvent.text = item.date

                itemView.setOnClickListener{
                    val entity = UserEntity(userEntity.nama, item.event, userEntity.guest)
                    val intent = Intent(itemView.context, EventGuestActivity::class.java)
                    intent.putExtra(EXTRA_USER, entity)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}
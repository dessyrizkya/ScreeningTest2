package com.dessy.screeningtest.ui.guest

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dessy.screeningtest.databinding.ItemGuestBinding
import com.dessy.screeningtest.model.GuestEntity
import com.dessy.screeningtest.model.UserEntity
import com.dessy.screeningtest.ui.EventGuestActivity
import com.dessy.screeningtest.util.Extra

class GuestAdapter(private val list: List<GuestEntity>, private val userEntity: UserEntity) : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val binding = ItemGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(binding, userEntity, parent.context)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    inner class GuestViewHolder(private val binding: ItemGuestBinding, private val userEntity: UserEntity, private val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GuestEntity) {
            binding.tvName.text = item.name
            val tanggal = item.birthdate.substring(8, 10)
            val bulan= item.birthdate.substring(5, 7)

            val tglInt = tanggal.toInt()
            val blnInt = bulan.toInt()

            if (isPrime(blnInt)) {
                binding.tvPrime.text = "Prime"
            } else {
                binding.tvPrime.text = "Not Prime"
            }

            var hp = ""

            if (tglInt%2 == 0) {
                hp = "Blackberry"

                if (tglInt%3 == 0) { hp = "iOS" }

            } else if (tglInt%3 == 0) {
                hp = "Android"
            } else {
                hp = "Feature Phone"
            }

            itemView.setOnClickListener {
                Toast.makeText(context, hp, Toast.LENGTH_SHORT).show()

                val entity = UserEntity(userEntity.nama, userEntity.event, item.name)
                val intent = Intent(itemView.context, EventGuestActivity::class.java)
                intent.putExtra(Extra.EXTRA_USER, entity)
                itemView.context.startActivity(intent)
            }

        }

        private fun isPrime(n: Int) : Boolean {
            var prime = false
            var nol = 0

            for (i in 1..n) {
                if (n%i == 0) {
                    nol++
                }
            }


            if (nol <= 2) {
                prime = true
            }

            return prime
        }
    }
}
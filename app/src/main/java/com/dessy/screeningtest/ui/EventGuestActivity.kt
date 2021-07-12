package com.dessy.screeningtest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dessy.screeningtest.R
import com.dessy.screeningtest.databinding.ActivityEventGuestBinding
import com.dessy.screeningtest.model.UserEntity
import com.dessy.screeningtest.ui.event.EventActivity
import com.dessy.screeningtest.ui.guest.GuestActivity
import com.dessy.screeningtest.util.Extra.EXTRA_USER

class EventGuestActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEventGuestBinding
    private lateinit var entity: UserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.getParcelableExtra<UserEntity>(EXTRA_USER)

        if (extras != null) {
            binding.tvName.text = extras.nama

            val event = if (extras.event.isEmpty()) {
                resources.getString(R.string.pilih_event)
            } else {
                extras.event
            }

            val guest = if (extras.guest.isEmpty()) {
                resources.getString(R.string.pilih_guest)
            } else {
                extras.guest
            }

            binding.btnGuest.text = guest
            binding.btnEvent.text = event
        }

        binding.btnEvent.setOnClickListener(this)
        binding.btnGuest.setOnClickListener(this)
    }

    private fun getEntity(): UserEntity {
        val nama = binding.tvName.text.toString()
        var event = binding.btnEvent.text.toString()
        var guest = binding.btnGuest.text.toString()

        if (event.equals(resources.getString(R.string.pilih_event))) {
            event = ""
        }

        if (guest.equals(resources.getString(R.string.pilih_guest))) {
            guest = ""
        }

        entity = UserEntity(nama, event, guest)

        return entity
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_event -> {
                val intent = Intent(this, EventActivity::class.java)
                intent.putExtra(EXTRA_USER, getEntity())
                startActivity(intent)
            }
            R.id.btn_guest -> {
                val intent = Intent(this, GuestActivity::class.java)
                intent.putExtra(EXTRA_USER, getEntity())
                startActivity(intent)
            }
        }
    }
}
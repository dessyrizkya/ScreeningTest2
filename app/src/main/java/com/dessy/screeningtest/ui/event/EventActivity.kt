package com.dessy.screeningtest.ui.event

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dessy.screeningtest.R
import com.dessy.screeningtest.databinding.ActivityEventBinding
import com.dessy.screeningtest.model.UserEntity
import com.dessy.screeningtest.util.Extra


class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding
    private lateinit var viewModel: EventViewModel
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.getParcelableExtra<UserEntity>(Extra.EXTRA_USER)

        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarEvent)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel = ViewModelProvider(this, ViewModelProvider
            .NewInstanceFactory())
            .get(EventViewModel::class.java)

        adapter = EventAdapter(viewModel.getEvents(), extras!!)
        with(binding) {
            rvEvent.layoutManager = LinearLayoutManager(this@EventActivity, LinearLayoutManager.VERTICAL, false)
            rvEvent.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_event, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_map -> {
                binding.rvEvent.visibility = View.GONE

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.map_container, MapsFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            binding.rvEvent.visibility = View.VISIBLE
        } else {
            finish()
            super.onBackPressed()
        }
    }
}
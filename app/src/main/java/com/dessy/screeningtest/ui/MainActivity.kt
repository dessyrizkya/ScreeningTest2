package com.dessy.screeningtest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dessy.screeningtest.databinding.ActivityMainBinding
import com.dessy.screeningtest.model.UserEntity
import com.dessy.screeningtest.util.Extra.EXTRA_USER

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFinish.setOnClickListener {

            if (binding.etName.text.trim().isEmpty()) {
                binding.etName.error = "Nama tidak boleh kosong"
                binding.etName.requestFocus()
                return@setOnClickListener

            } else {

                if (isPalindrome()) {
                    Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Not Palindrome", Toast.LENGTH_SHORT).show()
                }

                val nama = binding.etName.text.toString()
                val entity = UserEntity(nama)
                val intent = Intent(this, EventGuestActivity::class.java)
                intent.putExtra(EXTRA_USER, entity)
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome() : Boolean {
        var palindrome = false
        var nama = binding.etName.text.toString().lowercase()
        var length = nama.length
        var half = (length / 2) + 1

        Log.e("length", length.toString())

        if (length%2 == 1) {
            Log.e("nama", nama)

            if (nama.substring(0,half-1) == nama.substring(half,length).reversed()) {
                palindrome = true
            }

            nama = nama.replace(" ", "")
            length = nama.length
            half = (length / 2) + 1

            if (length % 2 == 1) {
                if (nama.substring(0,half-1) == nama.substring(half,length).reversed()) {
                    palindrome = true
                }
            }

        }

        return palindrome
    }
}
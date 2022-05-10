package com.lediya.dagger2.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lediya.dagger2.R
import com.lediya.dagger2.databinding.ActivityListScreenBinding

class ListScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_screen)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onMenuHomePressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onMenuHomePressed() {
        onBackPressed()
    }
}
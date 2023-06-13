package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.databinding.ActivityDisplayBinding
import com.example.app.databinding.ActivityMainBinding

class DisplayActivity : AppCompatActivity() {
    lateinit var binding : ActivityDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView.text = "Well, that escalated quickly!"
    }
}

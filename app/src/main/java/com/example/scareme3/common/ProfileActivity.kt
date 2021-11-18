package com.example.scareme3.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.scareme3.R
import com.example.scareme3.databinding.ActivityProfileBinding

class ProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


    }
}
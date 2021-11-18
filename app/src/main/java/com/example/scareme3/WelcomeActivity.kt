package com.example.scareme3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.scareme3.common.BaseActivity
import com.example.scareme3.databinding.ActivityWelcomeBinding

class WelcomeActivity : BaseActivity() {
    lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.btnSignUp.setOnClickListener {
            startActivity(SignInUpActivity::class.java)
        }

        binding.textSignIn.setOnClickListener {
            startActivity(SignInActivity::class.java)
        }
    }
}
package com.example.scareme3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.widget.Toast
import com.example.scareme3.api.App
import com.example.scareme3.common.BaseActivity
import com.example.scareme3.common.MainActivity
import com.example.scareme3.databinding.ActivitySignInBinding
import com.example.scareme3.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignInActivity : BaseActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            if (checkEmail() && checkPassword())
                autorization()
        }
    }

    private fun autorization() {
        val user = User (
            binding.editEmail.text.toString(),
            binding.editPassword.text.toString()
        )

        val disp = App.dm.api
            .autorization(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.code() == 200)
                    {
                        Toast.makeText(this, "Успешно", Toast.LENGTH_SHORT).show()
                        App.dm.setToken(it.body()!!.accessToken)
                        App.dm.setEmail(binding.editEmail.text.toString())
                        App.dm.setPassword(binding.editPassword.text.toString())
                        startActivity(MainActivity::class.java)
                    }
                },
                {
                    println(it.message)
                }
            )
    }

    private fun checkEmail() : Boolean{
        return if (binding.editEmail.text.toString().isNullOrEmpty()) {
            binding.editEmail.error = "Empty Email"
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.editEmail.text.toString())
                .matches()) {
            binding.editEmail.error = "Incorrect Email address"
            false
        }
        else true
    }

    private fun checkPassword() : Boolean{
        return if (binding.editPassword.text.toString().isNullOrEmpty()) {
            binding.editPassword.error = "Empty Password"
            false
        }
        else true
    }
}
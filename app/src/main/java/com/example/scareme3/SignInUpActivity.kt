package com.example.scareme3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.scareme3.api.App
import com.example.scareme3.common.BaseActivity
import com.example.scareme3.common.ProfileActivity
import com.example.scareme3.databinding.ActivitySignInUpBinding
import com.example.scareme3.model.User
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io

class SignInUpActivity : BaseActivity() {
    private lateinit var binding: ActivitySignInUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInUpBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            if (checkEmail() && checkPassword() && checkRepPassword())
                registration()
        }
    }

    private fun registration() {
        val user = User(
            binding.editEmail.text.toString(),
            binding.editPassword.text.toString()
        )

        val disp = App.dm.api
            .registration(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.code() == 400) {
                    binding.editPassword.error = "Short password"
                }
                else if (it.code() == 409) {
                    Toast.makeText(this, "This user already exists", Toast.LENGTH_SHORT).show()
                }
                else if (it.code() == 200) {
                    Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show()
                    App.dm.setToken(it.body()!!.accessToken)
                    App.dm.setEmail(binding.editEmail.text.toString())
                    App.dm.setPassword(binding.editPassword.text.toString())
                    startActivity(ProfileActivity::class.java)
                }
            }, {
                    println(it.message)
                }
            )
    }

    private fun checkEmail(): Boolean {
        return if (binding.editEmail.text.isNullOrEmpty()) {
            binding.editEmail.error = "Empty Email"
            false
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.editEmail.text.toString())
                .matches()) {
            binding.editEmail.error = "Incorrect Email address"
            false
        }
        else true
    }

    private fun checkPassword(): Boolean {
        return if (binding.editPassword.text.isNullOrEmpty()) {
            binding.editPassword.error = "Empty Password"
            false
        }
        else true
    }

    private fun checkRepPassword(): Boolean {
        return if (binding.editRepPassword.text.isNullOrEmpty()) {
            binding.editRepPassword.error = "Empty Confirm Password"
            false
        }
        else if (binding.editPassword.text.toString() != binding.editRepPassword.text.toString()) {
            binding.editRepPassword.error = "Password doesn't matches"
            false
        }
        else true
    }
}